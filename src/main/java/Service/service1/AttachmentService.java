package Service.service1;

import Service.entity.Attachment;
import Service.entity.Ticket;
import Service.repository.AttachmentRepository;
import Service.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AttachmentService {

    private static final String UPLOAD_DIR = "C:/Users/saich/OneDrive/Desktop/uploads";

    @Autowired
    private AttachmentRepository attachmentRepository;

    @Autowired
    private TicketRepository ticketRepository;

    public Attachment uploadFile(Long ticketId,
                                 MultipartFile file) throws IOException {

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket Not Found"));

        File dir = new File("C:/Users/saich/OneDrive/Desktop/uploads");

        if (!dir.exists()) {
            dir.mkdirs();
        }

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

        File destination = new File(dir, fileName);

        file.transferTo(destination);

        Attachment attachment = new Attachment();

        attachment.setFileName(file.getOriginalFilename());

        attachment.setFilePath(destination.getAbsolutePath());

        attachment.setUploadedDate(LocalDateTime.now());

        attachment.setTicket(ticket);

        return attachmentRepository.save(attachment);
    }

    public List<Attachment> getAttachments(Long ticketId) {

        return attachmentRepository.findByTicketId(ticketId);

    }

    public Attachment getAttachment(Long id) {

        return attachmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attachment Not Found"));

    }

    public void deleteAttachment(Long id) {

        Attachment attachment = getAttachment(id);

        File file = new File(attachment.getFilePath());

        if (file.exists()) {
            file.delete();
        }

        attachmentRepository.deleteById(id);
    }
}
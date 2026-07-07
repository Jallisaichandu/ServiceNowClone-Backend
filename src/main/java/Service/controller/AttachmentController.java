package Service.controller;

import Service.entity.Attachment;
import Service.service1.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/attachments")
@CrossOrigin(origins = "*")
public class AttachmentController {

    @Autowired
    private AttachmentService attachmentService;

    @PostMapping("/{ticketId}")
    public Attachment upload(
            @PathVariable Long ticketId,
            @RequestParam("file") MultipartFile file
    ) throws IOException {

        System.out.println("UPLOAD API HIT");


        return attachmentService.uploadFile(ticketId, file);

    }

    @GetMapping("/ticket/{ticketId}")
    public List<Attachment> getAttachments(
            @PathVariable Long ticketId) {

        return attachmentService.getAttachments(ticketId);

    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> download(
            @PathVariable Long id) {

        Attachment attachment = attachmentService.getAttachment(id);

        FileSystemResource resource =
                new FileSystemResource(attachment.getFilePath());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + attachment.getFileName() + "\"")
                .header(HttpHeaders.CONTENT_TYPE, "application/octet-stream")
                .body(resource);
    }

    @DeleteMapping("/{id}")
    public String delete(
            @PathVariable Long id) {

        attachmentService.deleteAttachment(id);

        return "Attachment Deleted Successfully";
    }
}
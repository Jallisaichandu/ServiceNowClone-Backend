package Service.service1;

import Service.entity.Ticket;
import Service.repository.TicketRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class ExcelService {

    @Autowired
    private TicketRepository ticketRepository;

    public byte[] exportTickets() throws Exception {

        List<Ticket> tickets = ticketRepository.findAll();

        XSSFWorkbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Tickets");

        // Header Row
        Row header = sheet.createRow(0);

        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("Title");
        header.createCell(2).setCellValue("Description");
        header.createCell(3).setCellValue("Priority");
        header.createCell(4).setCellValue("Status");
        header.createCell(5).setCellValue("Assigned User");
        header.createCell(6).setCellValue("Created Date");

        int rowNum = 1;

        for (Ticket ticket : tickets) {

            Row row = sheet.createRow(rowNum++);

            row.createCell(0).setCellValue(ticket.getId());

            row.createCell(1).setCellValue(ticket.getTitle());

            row.createCell(2).setCellValue(ticket.getDescription());

            row.createCell(3).setCellValue(ticket.getPriority());

            row.createCell(4).setCellValue(ticket.getStatus());

            row.createCell(5).setCellValue(
                    ticket.getAssignedUser() != null
                            ? ticket.getAssignedUser().getName()
                            : "Not Assigned"
            );

            row.createCell(6).setCellValue(
                    ticket.getCreatedDate() != null
                            ? ticket.getCreatedDate().toString()
                            : ""

            );
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        workbook.write(out);

        workbook.close();

        return out.toByteArray();
    }
}
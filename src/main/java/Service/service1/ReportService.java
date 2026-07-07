package Service.service1;

import Service.dto.ReportResponse;
import Service.repository.TicketRepository;
import Service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    public ReportResponse getReport() {

        ReportResponse report = new ReportResponse();

        report.setTotalTickets(
                ticketRepository.count()
        );

        report.setTotalUsers(
                userRepository.count()
        );

        report.setOpenTickets(
                ticketRepository.findAll()
                        .stream()
                        .filter(ticket ->
                                "OPEN".equalsIgnoreCase(ticket.getStatus()))
                        .count()
        );

        report.setClosedTickets(
                ticketRepository.findAll()
                        .stream()
                        .filter(ticket ->
                                "CLOSED".equalsIgnoreCase(ticket.getStatus()))
                        .count()
        );

        report.setHighPriorityTickets(
                ticketRepository.findAll()
                        .stream()
                        .filter(ticket ->
                                "HIGH".equalsIgnoreCase(ticket.getPriority()))
                        .count()
        );

        return report;
    }
}
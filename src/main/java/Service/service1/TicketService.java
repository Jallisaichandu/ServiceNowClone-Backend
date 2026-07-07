package Service.service1;

import Service.dto.TicketRequest;
import Service.entity.Ticket;
import Service.entity.User;
import Service.repository.TicketRepository;
import Service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Service.service1.NotificationService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketHistoryService historyService;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private EmailService emailService;

    public Ticket createTicket(TicketRequest request) {


        Ticket ticket = new Ticket();

        ticket.setTitle(request.getTitle());
        ticket.setDescription(request.getDescription());
        ticket.setPriority(request.getPriority());
        ticket.setStatus(request.getStatus());

        ticket.setCreatedDate(LocalDateTime.now());
        ticket.setUpdatedDate(LocalDateTime.now());

        if (request.getAssignedUserId() != null) {


            User user = userRepository.findById(
                    request.getAssignedUserId()
            ).orElse(null);

            ticket.setAssignedUser(user);

            Ticket savedTicket = ticketRepository.save(ticket);

// Save Ticket History
            historyService.saveHistory(
                    savedTicket.getId(),
                    "Ticket Created",
                    "System"
            );

// Create Notification
            notificationService.createNotification(
                    user.getEmail(),
                    "A new ticket '" + savedTicket.getTitle() + "' has been assigned to you."
            );
            emailService.sendEmail(
                    user.getEmail(),
                    "New Ticket Assigned - ServiceNow Clone",
                    "Hello " + user.getName() + ",\n\n" +
                            "A new ticket has been assigned to you.\n\n" +
                            "Title: " + savedTicket.getTitle() + "\n" +
                            "Priority: " + savedTicket.getPriority() + "\n" +
                            "Status: " + savedTicket.getStatus() + "\n\n" +
                            "Please log in to the application to view the ticket.\n\n" +
                            "Regards,\nServiceNow Clone"
            );

            return savedTicket;
        }

        return ticketRepository.save(ticket);
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id).orElse(null);
    }

    public Ticket updateTicket(Ticket ticket) {

        Ticket existingTicket = ticketRepository.findById(ticket.getId())
                .orElseThrow(() -> new RuntimeException("Ticket Not Found"));

        existingTicket.setTitle(ticket.getTitle());
        existingTicket.setDescription(ticket.getDescription());
        existingTicket.setPriority(ticket.getPriority());
        existingTicket.setStatus(ticket.getStatus());
        existingTicket.setUpdatedDate(LocalDateTime.now());

        Ticket updatedTicket = ticketRepository.save(existingTicket);

        historyService.saveHistory(
                updatedTicket.getId(),
                "Ticket Updated",
                "System"
        );

        if (updatedTicket.getAssignedUser() != null) {

            notificationService.createNotification(
                    updatedTicket.getAssignedUser().getEmail(),
                    "Your ticket '" +
                            updatedTicket.getTitle() +
                            "' has been updated."
            );

            if ("CLOSED".equalsIgnoreCase(updatedTicket.getStatus())) {

                notificationService.createNotification(
                        updatedTicket.getAssignedUser().getEmail(),
                        "Your ticket '" +
                                updatedTicket.getTitle() +
                                "' has been CLOSED."
                );
                emailService.sendEmail(
                        updatedTicket.getAssignedUser().getEmail(),
                        "Ticket Closed - ServiceNow Clone",
                        "Hello " + updatedTicket.getAssignedUser().getName() + ",\n\n" +
                                "Your ticket has been CLOSED successfully.\n\n" +
                                "Ticket Title : " + updatedTicket.getTitle() + "\n" +
                                "Priority     : " + updatedTicket.getPriority() + "\n" +
                                "Status       : " + updatedTicket.getStatus() + "\n\n" +
                                "Thank you for using ServiceNow Clone.\n\n" +
                                "Regards,\nAdmin Team"
                );
            }
        }

        return updatedTicket;
    }


    public List<Ticket> getMyTickets(String email) {

        return ticketRepository.findByAssignedUserEmail(email);

    }

    public void deleteTicket(Long id) {

        ticketRepository.deleteById(id);

    }

}


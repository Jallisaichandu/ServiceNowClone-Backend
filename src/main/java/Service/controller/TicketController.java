package Service.controller;

import Service.entity.Ticket;
import Service.service1.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import Service.dto.TicketRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import Service.service1.TicketService;

import java.util.List;

@RestController
@RequestMapping("/tickets")
@CrossOrigin(origins = "*")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    // Create Ticket


    @PostMapping
    public Ticket createTicket(@RequestBody TicketRequest request) {

        return ticketService.createTicket(request);

    }

    // Get All Tickets
    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    // Get Ticket By Id
    @GetMapping("/{id}")
    public Ticket getTicketById(@PathVariable Long id) {
        return ticketService.getTicketById(id);
    }


    // Update Ticket
    @PutMapping("/{id}")
    public Ticket updateTicket(
            @PathVariable Long id,
            @RequestBody Ticket ticket) {

        ticket.setId(id);
        return ticketService.updateTicket(ticket);
    }

    // Delete Ticket
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public String deleteTicket(@PathVariable Long id) {

        ticketService.deleteTicket(id);

        return "Ticket Deleted Successfully";
    }
    @GetMapping("/my")
    public List<Ticket> myTickets(Authentication authentication) {
        System.out.println("===== /tickets/my =====");
        System.out.println("Authentication = " + authentication);

        if (authentication != null) {
            System.out.println("User = " + authentication.getName());
        }

        return ticketService.getMyTickets(authentication.getName());


    }

}
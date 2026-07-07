package Service.controller;

import Service.entity.TicketHistory;
import Service.service1.TicketHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/history")
public class TicketHistoryController {

    @Autowired
    private TicketHistoryService historyService;

    @GetMapping("/{ticketId}")
    public List<TicketHistory> getHistory(
            @PathVariable Long ticketId
    ) {

        return historyService.getHistory(ticketId);

    }

}
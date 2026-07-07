package Service.service1;

import Service.entity.TicketHistory;
import Service.repository.TicketHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketHistoryService {

    @Autowired
    private TicketHistoryRepository historyRepository;

    public void saveHistory(
            Long ticketId,
            String action,
            String userName
    ) {

        TicketHistory history = new TicketHistory();

        history.setTicketId(ticketId);
        history.setAction(action);
        history.setUserName(userName);
        history.setActionDate(LocalDateTime.now().toString());

        historyRepository.save(history);
    }

    public List<TicketHistory> getHistory(Long ticketId) {

        return historyRepository.findByTicketId(ticketId);

    }

}
package Service.service1;

import Service.entity.Notification;
import Service.entity.User;
import Service.repository.NotificationRepository;
import Service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    // Create Notification
    public Notification createNotification(
            String email,
            String message) {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new RuntimeException("User Not Found");
        }

        Notification notification = new Notification();

        notification.setMessage(message);
        notification.setRead(false);
        notification.setCreatedDate(LocalDateTime.now());
        notification.setUser(user);

        return notificationRepository.save(notification);

    }


    // Get User Notifications
    public List<Notification> getNotifications(String email) {

        return notificationRepository
                .findByUserEmailOrderByCreatedDateDesc(email);
    }

    // Mark Notification as Read
    public Notification markAsRead(Long id) {

        Notification notification = notificationRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Notification Not Found"));

        notification.setRead(true);

        return notificationRepository.save(notification);
    }

    // Delete Notification
    public void deleteNotification(Long id) {

        notificationRepository.deleteById(id);


    }

}
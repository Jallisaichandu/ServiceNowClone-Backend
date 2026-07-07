package Service.controller;

import Service.entity.Notification;
import Service.service1.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@CrossOrigin(origins = "*")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    // Get Logged-in User Notifications
    @GetMapping
    public List<Notification> getNotifications(
            Authentication authentication) {

        return notificationService.getNotifications(
                authentication.getName()
        );
    }

    // Mark Notification as Read
    @PutMapping("/{id}/read")
    public Notification markAsRead(
            @PathVariable Long id) {

        return notificationService.markAsRead(id);
    }

    // Delete Notification
    @DeleteMapping("/{id}")
    public String deleteNotification(
            @PathVariable Long id) {

        notificationService.deleteNotification(id);

        return "Notification Deleted Successfully";
    }
}
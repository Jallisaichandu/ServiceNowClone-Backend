package Service.controller;

import Service.service1.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/email/test")
    public String sendTestEmail() {

        emailService.sendEmail(
                "saichandujalli123@gmail.com",
                "ServiceNow Clone Test Email",
                "Congratulations! Your Spring Boot email configuration is working successfully."
        );

        return "Test Email Sent Successfully!";
    }
}
package Service.controller;

import Service.entity.User;
import Service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import Service.dto.ProfileResponse;

@RestController
@RequestMapping("/profile")
@CrossOrigin(origins = "*")
public class ProfileController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ProfileResponse getProfile(Authentication authentication) {

        String email = authentication.getName();

        User user = userRepository.findByEmail(email);

        return new ProfileResponse(
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }
}


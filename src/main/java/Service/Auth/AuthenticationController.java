package Service.Auth;

import Service.entity.User;
import Service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import Service.security.JwtService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public AuthenticationResponse login(
            @RequestBody AuthenticationRequest request) {

        System.out.println("Email Received: " + request.getEmail());
        System.out.println("Password Received: " + request.getPassword());

        User user = userRepository.findByEmail(request.getEmail());

        if (user == null) {
            System.out.println("User Not Found");
            throw new RuntimeException("User Not Found");
        }

        System.out.println("User Found: " + user.getEmail());
        System.out.println("Password in DB: " + user.getPassword());

        if (!user.getPassword().equals(request.getPassword())) {
            System.out.println("Password Mismatch");
            throw new RuntimeException("Invalid Password");
        }

        System.out.println("Login Successful");

        String token = jwtService.generateToken(user.getEmail());

        return new AuthenticationResponse(
                token,
                user.getRole(),
                user.getName()
        );
    }
    public static class AuthenticationResponse {

        private String token;
        private String role;
        private String name;

        public AuthenticationResponse() {
        }

        public AuthenticationResponse(
                String token,
                String role,
                String name) {

            this.token = token;
            this.role = role;
            this.name = name;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {

        User existingUser = userRepository.findByEmail(request.getEmail());

        if (existingUser != null) {
            return "Email already exists";
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        // Save password as plain text (same as your current login)
        user.setPassword(request.getPassword());

        // Every new user gets USER role
        user.setRole("USER");

        userRepository.save(user);

        return "Registration Successful";
    }
}


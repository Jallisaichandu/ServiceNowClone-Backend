package Service.service1;

import Service.entity.User;
import Service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    public User updateUser(User user) {
        return userRepository.save(user);
    }
    public void changePassword(
            String email,
            String currentPassword,
            String newPassword
    ) {

        User user = userRepository.findByEmail(email);
        System.out.println("Logged In Email: " + email);
        System.out.println("Password From DB: " + user.getPassword());
        System.out.println("Current Password Entered: " + currentPassword);



        // If you are STILL using plain text passwords
        if (!user.getPassword().equals(currentPassword)) {
            throw new RuntimeException("Current Password Incorrect");
        }

        // If later you switch to BCrypt, replace the above with:
        // if (!passwordEncoder.matches(currentPassword, user.getPassword()))

        user.setPassword(newPassword);

        userRepository.save(user);
    }
}
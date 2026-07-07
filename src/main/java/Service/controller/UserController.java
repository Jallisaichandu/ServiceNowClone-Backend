package Service.controller;

import Service.entity.User;
import Service.service1.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;
import Service.dto.ChangePasswordRequest;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/users")




public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }


    @PutMapping("/{id}")
    public User updateUser(
            @PathVariable Long id,
            @RequestBody User user) {

        user.setId(id);

        return userService.updateUser(user);
    }
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);


        return "User Deleted Successfully";
    }

    @PostMapping("/change-password")
    public String changePassword(

            Authentication authentication,

            @RequestBody ChangePasswordRequest request

    ) {
        System.out.println("=== CHANGE PASSWORD API HIT ===");

        String email = authentication.getName();

        userService.changePassword(

                email,

                request.getCurrentPassword(),

                request.getNewPassword()

        );

        return "Password Changed Successfully";

    }
}
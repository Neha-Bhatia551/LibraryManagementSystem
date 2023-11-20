package edu.depaul.cdm.se452.fall2023group1.user;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Log4j2
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public User addAUser(@RequestBody User user)  throws UserNotFoundException{
        return userService.add(user);
    }

    @DeleteMapping
    public void delete(Long id) {
        userService.delete(id);
    }

    @GetMapping
    public List<User> getAllUsers() throws UserNotFoundException{
        return userService.list();
    }

}

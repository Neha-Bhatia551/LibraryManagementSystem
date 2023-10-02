package edu.depaul.cdm.se452.fall2023group1.user;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Log4j2
public class UserController {
    @Autowired
    private UserRepository repository;

    @PostMapping("/add/{id}")
    public User addAUser(@RequestBody User user) {
        log.info("adding a user");
        return repository.save(user);
    }

    @DeleteMapping("/{id}")
    public void delete(User user) {
        log.info("deleting user", user);
        repository.delete(user);

    }

    @GetMapping
    public List<User> getAllUsers() {
        log.info("Getting all users");
        return repository.findAll();
    }
}

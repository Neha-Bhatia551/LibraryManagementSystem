package edu.depaul.cdm.se452.fall2023group1.user;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@Log4j2
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping
    public List<User> list() {
        return service.list();
    }
}

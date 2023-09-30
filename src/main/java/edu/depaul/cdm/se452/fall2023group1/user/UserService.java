package edu.depaul.cdm.se452.fall2023group1.user;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class UserService {
    @Autowired
    private UserRepository repo;

    public List<User> list() {
        log.traceEntry("getting user list");
        List<User> userList = repo.findAll().stream().toList();
        log.traceEntry("end of user list", userList);
        return userList;
    }
}

package edu.depaul.cdm.se452.fall2023group1.user;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class UserService {
    @Autowired
    private UserRepository repository;

    public List<User> list() {
        return repository.findAll();
    }


    public User add(User user) {
        repository.save(user);
        return user;
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new UserNotFoundException("User with ID " + id + " not found");
        }
        repository.deleteById(id);
    }

}

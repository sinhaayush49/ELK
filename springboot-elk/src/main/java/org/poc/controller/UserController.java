package org.poc.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.poc.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable int id) {
        List<User> users = getUsers();
        User user = users.stream().
                filter(u -> u.getId() == id).findAny().orElse(null);
        if (user != null) {
            logger.info("user found : {}", user);
            return user;
        } else {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("User Not Found with ID : {}", id);
            }
            return new User();
        }
    }
    private List<User> getUsers() {
        return Stream.of(new User(1, "John"),
                        new User(2, "Shyam"),
                        new User(3, "Rony"),
                        new User(4, "Mak"))
                .collect(Collectors.toList());
    }
}
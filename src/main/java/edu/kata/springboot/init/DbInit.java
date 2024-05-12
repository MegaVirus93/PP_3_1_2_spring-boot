package edu.kata.springboot.init;

import edu.kata.springboot.model.User;
import edu.kata.springboot.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DbInit {

    private final UserService userService;

    @Autowired
    DbInit(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    private void postConstruct() {
        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        User user4 = new User("User4", "Lastname4", "user4@mail.ru");

        userService.add(user2);
        userService.add(user3);
        userService.add(user1);
        userService.add(user4);
    }
}

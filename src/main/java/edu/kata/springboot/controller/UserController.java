package edu.kata.springboot.controller;

import edu.kata.springboot.model.User;
import edu.kata.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;

        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        User user4 = new User("User4", "Lastname4", "user4@mail.ru");

        userService.add(user2);
        userService.add(user3);
        userService.add(user1);
        userService.add(user4);
    }

    @GetMapping()
    public String printUsers(Model model) {
        List<User> userList = userService.listUsers();
        model.addAttribute("user_list", userList);
        return "user-list";
    }

    @GetMapping(value = "/view")
    public String viewUser(@RequestParam(value = "id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user-view";
    }

    @GetMapping(value = "/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "user-new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user-new";
        }
        userService.add(user);
        return "redirect:/";
    }

    @GetMapping(value = "/edit")
    public String edit(@RequestParam(value = "id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user-edit";
    }

    @PostMapping(value = "/save")
    public String update(@RequestParam(value = "id") long id, @ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user-edit";
        }
        userService.update(user, id);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam(value = "id") long id) {
        userService.delete(id);
        return "redirect:/";
    }

}

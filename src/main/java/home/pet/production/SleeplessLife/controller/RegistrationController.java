package home.pet.production.SleeplessLife.controller;

import home.pet.production.SleeplessLife.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    @Autowired
    UserService userService;

    @GetMapping("/registration")
    public String addUser() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addNewUser(@RequestParam String username,
                             @RequestParam String password,
                             @RequestParam String email, Model model) {
        boolean result = userService.createUser(username, password, email);
        if(!result) {
            String message = "Пользователь с данным email уже существует";
            model.addAttribute("message", message);
            return "registration";
        }

        return "redirect:/user/list";
    }
}

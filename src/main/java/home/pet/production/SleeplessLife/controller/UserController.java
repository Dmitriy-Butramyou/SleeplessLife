package home.pet.production.SleeplessLife.controller;


import home.pet.production.SleeplessLife.domain.Role;
import home.pet.production.SleeplessLife.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/list")
    public String userList(Model model) {
        model.addAttribute("users", userService.findAll());
        return "userList";
    }

    @GetMapping("/delete/{customerLink}")
    public String deleteUser(@PathVariable String customerLink) {
        userService.deleteUser(customerLink);
        return "redirect:/user/list";
    }

    @GetMapping("/edit/{customerLink}")
    public String editUser(@PathVariable String customerLink, Model model) {
        model.addAttribute("user", userService.findOne(customerLink));
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PostMapping("/edit/{customerLink}")
    public String doEditUser(@PathVariable String customerLink,
                             @RequestParam Map<String, String> form,
                             @RequestParam String newLink,
                             @RequestParam String name,
                             @RequestParam String email,
                             @RequestParam String password) {

        userService.updateUser(customerLink, newLink, name, email, password, form);

        return "redirect:/user/list";
    }

    @GetMapping("/{customerLink}")
    public String userPage(@PathVariable String customerLink) {
        return "userPage";
    }
}

package home.pet.production.SleeplessLife.controller;


import home.pet.production.SleeplessLife.domain.Role;
import home.pet.production.SleeplessLife.model.User;
import home.pet.production.SleeplessLife.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

  @Autowired
  UserService userService;

  @GetMapping("/list")
  @PreAuthorize("hasAuthority('ADMIN')")
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
                           @RequestParam Map<String, String> form) {

    User updateUser = userService.updateUser(customerLink, form);

    return "redirect:/user/edit/" + updateUser.getCustomerLink();
  }

  @GetMapping("/{customerLink}")
  public String userPage(@PathVariable String customerLink) {
    return "userPage";
  }

  @PostMapping("/uploadPersonalPhoto/{customerLink}")
  public String uploadPersonalPhoto(@PathVariable String customerLink) {

    return "userPage";
  }
}

package home.pet.production.SleeplessLife.controller;


import home.pet.production.SleeplessLife.domain.Role;
import home.pet.production.SleeplessLife.model.User;
import home.pet.production.SleeplessLife.service.ImagesService;
import home.pet.production.SleeplessLife.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * Контролер для работы с пользователем
 *
 * @author D.Butramyou
 */
@Controller
@RequestMapping("/user")
public class UserController {

  @Autowired
  UserService userService;

  @Autowired
  ImagesService imagesService;

  /**
   * Вывод списка пользователей
   * @param model карта строк
   * @return список пользователей
   */
  @GetMapping("/list")
  @PreAuthorize("hasAuthority('ADMIN')")
  public String userList(Model model) {
    model.addAttribute("users", userService.findAll());
    return "userList";
  }

  /**
   * Удаление выбраного пользователя
   * @param customerLink уникальная ссылка пользователя
   * @return список пользователей
   */
  @GetMapping("/delete/{customerLink}")
  public String deleteUser(@PathVariable String customerLink) {
    userService.deleteUser(customerLink);
    return "redirect:/user/list";
  }

  /**
   * Страница изменения данных пользователя
   * @param customerLink уникальная ссылка пользователя
   * @param model карта строк
   * @return страница изменения данных пользователя
   */
  @GetMapping("/edit/{customerLink}")
  public String editUser(@PathVariable String customerLink, Model model) {
    model.addAttribute("user", userService.findOne(customerLink));
    model.addAttribute("roles", Role.values());
    return "userEdit";
  }

  /**
   * Изменение данных пользователя
   * @param customerLink уникальная ссылка пользователя
   * @param form карта полей пользователя
   * @return страница изменения данных пользователя
   */
  @PostMapping("/edit/{customerLink}")
  public String doEditUser(@PathVariable String customerLink,
                           @RequestParam Map<String, String> form) {

    User updateUser = userService.updateUser(customerLink, form);

    return "redirect:/user/edit/" + updateUser.getCustomerLink();
  }

  /**
   * Страница пользователя
   * @param customerLink уникальная ссылка пользователя
   * @param model карта строк
   * @return Страница пользователя
   */
  @GetMapping("/{customerLink}")
  public String userPage(@PathVariable String customerLink, Model model) {
    User user = userService.findOne(customerLink);
    if (user.getPersonalPhotos().size() > 0) {
      model.addAttribute("pathToPersonalPhoto", user.getPersonalPhotos().get(user.getPersonalPhotos().size() - 1));
    }

    return "userPage";
  }

  /**
   * Загрузка аватарки пользователя
   * @param customerLink уникальная ссылка пользователя
   * @param photo загружаемое изображение
   * @return Страница пользователя
   */
  @PostMapping("/uploadPersonalPhoto/{customerLink}")
  public String uploadPersonalPhoto(@PathVariable String customerLink,
                                    @RequestParam("photo") MultipartFile photo) throws IOException {

    imagesService.uploadPersonalPhoto(photo, customerLink);
    return "redirect:/user/" + customerLink;
  }
}

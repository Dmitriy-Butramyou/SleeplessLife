package home.pet.production.SleeplessLife.service;

import home.pet.production.SleeplessLife.domain.Role;
import home.pet.production.SleeplessLife.model.User;
import home.pet.production.SleeplessLife.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Сервис для работы с пользователем
 *
 * @author D.Butramyou
 */
@Service
public class UserService implements UserDetailsService {

  @Autowired
  UserRepo userRepo;

  @Autowired
  PasswordEncoder passwordEncoder;

  /**
   * Создание root пользователя при первом старте
   */
  @PostConstruct
  public void init() {
    String email = "admin";
    User user = userRepo.findByEmail(email);
    if (user == null) {
      user = new User();
      user.setName("admin");
      user.setPassword(passwordEncoder.encode("admin"));
      user.setCustomerLink("admin");
      user.setEmail(email);
      user.setRoles(Stream.of(Role.USER, Role.ADMIN).collect(Collectors.toSet()));
      userRepo.save(user);
    }
  }

  public List<User> findAll() {
    return userRepo.findAll();
  }

  public User findOne(String customerLink) {
    return userRepo.findByCustomerLink(customerLink);
  }

  public void deleteUser(String customerLink) {
    userRepo.deleteById((userRepo.findByCustomerLink(customerLink)).getId());
  }

  /**
   * Создание нового пользователя при валидных данных
   *
   * @param email по нему идет проверка в базе, пользователей с таким же
   *              email не должно сеществовать
   * @return true если пользователь добавлен, false если нет
   */
  //todo добавить ограничения по длине и качестве пароля, проверять имя
  public Boolean createUser(String name, String password, String email) {
    if (name != null && !name.isEmpty()
        && password != null && !password.isEmpty()
        && !email.isEmpty() && userRepo.findByEmail(email) == null) {

      String customUuid = UUID.randomUUID().toString();
      User newUser = new User(customUuid, name, passwordEncoder.encode(password), email);
      newUser.setRoles(Collections.singleton(Role.USER));
      userRepo.save(newUser);
      return true;
    }
    return false;
  }

  /**
   * Обновление сущ. записи пользователя.
   *
   * @param customerLink уникальная ссылка пользователя, доступна к изменения, изначально UUID
   * @param form         роли
   */
  public User updateUser(String customerLink, Map<String, String> form) {
    User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    User updateUser = userRepo.findByCustomerLink(customerLink);
    //Если новая ссылка пользователя уникальна, то изменяем стандартную
    if (validateFields(customerLink, form.get("newLink")) && userRepo.findByCustomerLink(form.get("newLink")) == null) {
      updateUser.setCustomerLink(form.get("newLink"));
    }
    //TODO добавить валидацию
    updateUser.setName(validateFields(updateUser.getUsername(), form.get("name"))
        ? form.get("name") : updateUser.getUsername());
    updateUser.setEmail(validateFields(updateUser.getEmail(), form.get("email")) && userRepo.findByEmail(form.get("email")) == null
        ? form.get("email") : updateUser.getEmail());
    //TODO отправлять на почту письмо подтверждения
    updateUser.setPassword((form.get("password") != null && !form.get("password").isEmpty())
        ? passwordEncoder.encode(form.get("password")) : updateUser.getPassword());

    if (currentUser.getRoles().contains(Role.ADMIN)) {
      Set<String> roles = Arrays.stream(Role.values())
          .map(Role::getAuthority)
          .collect(Collectors.toSet());
      updateUser.getRoles().clear();

      for (String key : form.keySet()) {
        if (roles.contains(key)) {
          updateUser.getRoles().add(Role.valueOf(key));
        }
      }
    }
    userRepo.save(updateUser);

    return updateUser;
  }

  /**
   * Базовая валидация полей при изменении
   *
   * @param currentValue текущее значение поля
   * @param newValue     новое значение поля
   * @return действительно ли значение
   */
  private boolean validateFields(String currentValue, String newValue) {
    return newValue != null && !newValue.isEmpty() && !newValue.equals(currentValue);
  }

  /**
   * Метод для Security, производит поиск пользователей и их ролей
   *
   * @param email пользователя (поле уникально для всех)
   * @throws UsernameNotFoundException
   */
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = userRepo.findByEmail(email);
    if (user == null) {
      throw new UsernameNotFoundException("Invalid User");
    } else {
      return user;
    }
  }
}

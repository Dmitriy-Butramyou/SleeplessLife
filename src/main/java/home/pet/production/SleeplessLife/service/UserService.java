package home.pet.production.SleeplessLife.service;

import home.pet.production.SleeplessLife.domain.Role;
import home.pet.production.SleeplessLife.model.User;
import home.pet.production.SleeplessLife.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        if(user == null) {
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
     * @param email по нему идет проверка в базе, пользователей с таким же
     *              email не должно сеществовать
     * @return true если пользователь добавлен, false если нет
     */
    //todo добавить ограничения по длине и качестве пароля, проверять имя
    public Boolean createUser(String name, String password, String email) {
        if(name != null && !name.isEmpty()
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
     * @param customerLink уникальная ссылка пользователя пользователя/ доступна к изменения, изначально UUID
     * @param form роли
     */
    public void updateUser(String customerLink, String newLink, String name, String email, String password, Map<String, String> form) {
        User updateUser = userRepo.findByCustomerLink(customerLink);
        //Если новая ссылка пользователя уникальна, то изменяем стандартную
        if(newLink !=null && !newLink.isEmpty() && userRepo.findByCustomerLink(newLink) == null) {
            updateUser.setCustomerLink(newLink);
        }
        //TODO добавить валидацию
        updateUser.setName((name != null && !name.isEmpty()) ? name : updateUser.getUsername());
        updateUser.setEmail((email != null && !email.isEmpty())? email : updateUser.getEmail());
        //TODO отправлять на почту письмо подтверждения
        updateUser.setPassword((password != null && !password.isEmpty()) ? passwordEncoder.encode(password) : updateUser.getPassword());

        //TODO Изменение ролей, по хорошему нужно вынести в другое место
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::getAuthority)
                .collect(Collectors.toSet());
        updateUser.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                updateUser.getRoles().add(Role.valueOf(key));
            }
        }
        userRepo.save(updateUser);
    }

    /**
     * Метод для Security, производит поис пользователей и их ролей
     * @param email пользователя (поле уникально для всех)
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email);
        if(user == null) {
            throw new UsernameNotFoundException("Invalid User");
        } else {
         return user;
        }
    }
}

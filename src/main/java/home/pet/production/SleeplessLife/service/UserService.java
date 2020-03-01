package home.pet.production.SleeplessLife.service;

import home.pet.production.SleeplessLife.domain.Role;
import home.pet.production.SleeplessLife.model.User;
import home.pet.production.SleeplessLife.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

    @PostConstruct
    public void init() {
        String email = "admin";
        User user = userRepo.findByEmail(email);
        if(user == null) {
            user = new User();
            user.setUsername("admin");
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

    public Boolean createUser(String username, String password, String email) {
        boolean result = false;
        User user = userRepo.findByEmail(email);
        if (user == null) {
            String customUuid = UUID.randomUUID().toString();
            User newUser = new User(customUuid, username, passwordEncoder.encode(password), email);
            newUser.setRoles(Collections.singleton(Role.USER));
            userRepo.save(newUser);
            result = true;
        }
        return result;
    }

    public void updateUser(String customerLink, String newLink, String username, String email, String password, Map<String, String> form) {
        User updateUser = userRepo.findByCustomerLink(customerLink);

        //Если новая ссылка пользователя уникальна, то изменяем стандартную
        if(newLink !=null && userRepo.findByCustomerLink(newLink) == null) {
            updateUser.setCustomerLink(newLink);
        }

        //TODO добавить валидацию
        updateUser.setUsername(username != null ? username : updateUser.getUsername());
        updateUser.setEmail(email != null ? email : updateUser.getEmail());
        //TODO отправлять на почту письмо подтверждения
        updateUser.setPassword(password != null ? passwordEncoder.encode(password) : updateUser.getPassword());

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

    //Производит поиск по email
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email);
        if(user == null) {
            throw new UsernameNotFoundException("Invalid User");
        } else {
            Set<GrantedAuthority> grantedAuthorities = user.getRoles()
                    .stream()
                    .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
                    .collect(Collectors.toSet());
         return new org
                 .springframework
                 .security
                 .core
                 .userdetails
                 .User(user.getEmail(), user.getPassword(), grantedAuthorities);
        }
    }
}

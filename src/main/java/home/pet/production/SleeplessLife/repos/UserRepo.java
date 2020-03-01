package home.pet.production.SleeplessLife.repos;

import home.pet.production.SleeplessLife.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepo extends JpaRepository<User, UUID> {

    User findByEmail(String email);
    User findByCustomerLink(String customerLink);
}

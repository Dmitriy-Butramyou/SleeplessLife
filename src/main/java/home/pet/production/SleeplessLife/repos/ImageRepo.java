package home.pet.production.SleeplessLife.repos;

import home.pet.production.SleeplessLife.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ImageRepo extends JpaRepository<Image, UUID> {

  Image getById(UUID id);
}

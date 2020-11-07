package home.pet.production.SleeplessLife.service;

import home.pet.production.SleeplessLife.model.User;
import home.pet.production.SleeplessLife.repos.UserRepo;
import home.pet.production.SleeplessLife.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * Сервис для работы с изображениями
 *
 * @author D.Butramyou
 */
@Service
public class ImagesService {

  @Autowired
  UserRepo userRepo;

  @Value("${upload.path}")
  private String uploadPath;

  /**
   * Загрузка аватарки пользователя
   * @param image изображение
   * @param linkToUser уникальная ссылка пользователя
   */
  public void uploadPersonalPhoto(MultipartFile image, String linkToUser) throws IOException {
    if (image != null && !image.getOriginalFilename().isEmpty()) {
      String pathToImage = saveImage(image);
      User user = userRepo.findByCustomerLink(linkToUser);
      List<String> personalPhotos = user.getPersonalPhotos();
      personalPhotos.add(pathToImage);
      user.setPersonalPhotos(personalPhotos);

      userRepo.save(user);
    }
  }

  /**
   * Сохранение изображения
   * @param image изображение
   * @return путь к сохраненному изображению
   */
  private String saveImage(MultipartFile image) throws IOException {
    String generatedPath = FileUtil.generatePath();
    String path = uploadPath + generatedPath;
    File uploadDir = new File(path);
    if (!uploadDir.exists()) {
      uploadDir.mkdirs();
    }
    String uuid = UUID.randomUUID().toString();
    String resultFileName = uuid + "-!" + image.getOriginalFilename();
    image.transferTo(new File(path + resultFileName));

    return generatedPath + resultFileName;
  }
}

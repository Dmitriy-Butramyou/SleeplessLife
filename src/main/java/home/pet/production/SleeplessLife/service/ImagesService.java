package home.pet.production.SleeplessLife.service;

import home.pet.production.SleeplessLife.model.Image;
import home.pet.production.SleeplessLife.model.User;
import home.pet.production.SleeplessLife.repos.ImageRepo;
import home.pet.production.SleeplessLife.repos.UserRepo;
import home.pet.production.SleeplessLife.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Сервис для работы с изображениями
 *
 * @author D.Butramyou
 */
@Service
public class ImagesService {

  @Autowired
  UserRepo userRepo;
  @Autowired
  ImageRepo imageRepo;

  @Value("${upload.path}")
  private String uploadPath;

  /**
   * Загрузка аватарки пользователя
   * @param uploadedImage изображение
   * @param user пользователь загрузивший изображение
   */
  public void uploadPersonalPhoto(MultipartFile uploadedImage, User user) throws IOException {
    if (uploadedImage != null && !uploadedImage.getOriginalFilename().isEmpty()) {
      String pathToImage = saveImage(uploadedImage);
      Image image = new Image(pathToImage, null, true, user);

      image = imageRepo.save(image);
      user.setImages(image);
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

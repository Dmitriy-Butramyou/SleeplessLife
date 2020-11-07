package home.pet.production.SleeplessLife.utils;

import java.time.LocalDateTime;

/**
 * Утилиты для работы с файлами
 *
 * @author D.Butramyou
 */
public class FileUtil {

  /**
   * Генерция уникального пути
   * @return уникальный путь
   */
  public static String generatePath() {
    StringBuilder path = new StringBuilder();
    LocalDateTime localDate = LocalDateTime.now();
    path.append("/")
        .append(localDate.getYear())
        .append("/")
        .append(localDate.getMonthValue())
        .append("/")
        .append(localDate.getDayOfMonth())
        .append("/")
        .append(localDate.getHour())
        .append("/");

    return path.toString();
  }
}

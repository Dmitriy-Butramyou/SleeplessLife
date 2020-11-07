package home.pet.production.SleeplessLife.converter;

import javax.persistence.AttributeConverter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Конвертер для сохранения List<String> в виде строки в поле таблицы БД
 *
 * @author D.Butramyou
 */
public class StringListConverter implements AttributeConverter<List<String>, String> {

  /**
   * Соединение в одну строку всех объектов списка
   * @param list лист строк
   * @return сгенерированная строка
   */
  @Override
  public String convertToDatabaseColumn(List<String> list) {
    if (list != null) {
      return String.join(":;", list);
    }
    return null;
  }

  /**
   * Парсинг строки в список строк
   * @param joined список в сиде строки
   * @return собранный список строк
   */
  @Override
  public List<String> convertToEntityAttribute(String joined) {
    if (joined != null) {
      return new ArrayList<>(Arrays.asList(joined.split(":;")));
    }
    return new ArrayList<>();
  }
}

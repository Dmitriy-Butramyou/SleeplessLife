package home.pet.production.SleeplessLife.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Модель описывающая класс изображений загружаемых пользователями
 *
 * @author D.Butramyou
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Image {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(updatable = false, nullable = false)
  @EqualsAndHashCode.Include
  String id;
  String path;
  String pathToSmallVersion;
  boolean isAvatar;

  @ManyToMany(mappedBy = "images")
  private Set<User> users = new HashSet<>();

  public Image(String path, String pathToSmallVersion, boolean isAvatar, User user) {
    this.path = path;
    this.pathToSmallVersion = pathToSmallVersion;
    this.isAvatar = isAvatar;
    this.users.add(user);
  }
}

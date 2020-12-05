package home.pet.production.SleeplessLife.model;

import home.pet.production.SleeplessLife.domain.Role;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Модель описывающая класс пользователя
 *
 * @author D.Butramyou
 */
@Entity
@Table(name = "usr")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User implements UserDetails {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(updatable = false, nullable = false)
  @EqualsAndHashCode.Include
  private UUID id;
  private String customerLink;
  private String name;
  private String email;
  private String password;

  @ManyToMany(cascade = { CascadeType.ALL })
  @JoinTable(
      name = "Users_Images",
      joinColumns = { @JoinColumn(name = "user_id") },
      inverseJoinColumns = { @JoinColumn(name = "image_id") }
  )
  Set<Image> images = new HashSet<>();

  @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
  @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
  @Enumerated(EnumType.STRING)
  private Set<Role> roles;


  public User(String customerLink, String name, String password, String email) {
    this.customerLink = customerLink;
    this.name = name;
    this.password = password;
    this.email = email;
  }

  public void setImages(Image images) {
    this.images.add(images);
  }

  public boolean isAdmin() {
    return roles.contains(Role.ADMIN);
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return getRoles();
  }
}

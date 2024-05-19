package org.smarthammer.reservation.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditOverride;
import org.smarthammer.reservation.domain.Form.user.SignUpForm;

@Getter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@AuditOverride(forClass = BaseEntity.class)
public class User extends BaseEntity{

  @Id
  @Column(nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String email;

  @Enumerated(EnumType.STRING)
  private Type type;

  private String password;
  private String name;
  private String phone;

  @Setter
  private boolean partnership;

  public static User formToEntity(SignUpForm form, Type type) {
    return User.builder()
        .email(form.getEmail())
        .password(form.getPassword())
        .name(form.getName())
        .type(type)
        .phone(form.getPhone())
        .build();
  }
}

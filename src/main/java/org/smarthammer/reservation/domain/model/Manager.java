package org.smarthammer.reservation.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.AuditOverride;
import org.smarthammer.reservation.domain.SignUpForm;

import java.util.Locale;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@AuditOverride(forClass = BaseEntity.class)
public class Manager extends BaseEntity{
    @Id
    @Column(name = "manager_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long manager_id;

    @Column(unique = true)
    private String email;

    private String password;
    private String name;
    private String phone;
    private Boolean partnership;

    public static Manager from(SignUpForm form) {
        return Manager.builder()
                .email(form.getEmail().toLowerCase(Locale.ROOT))
                .password(form.getPassword())
                .name(form.getName())
                .phone(form.getPhone())
                .partnership(true)
                .build();
    }
}

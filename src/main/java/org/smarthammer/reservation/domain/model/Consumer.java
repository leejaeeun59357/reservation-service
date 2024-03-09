package org.smarthammer.reservation.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.envers.AuditOverride;
import org.smarthammer.reservation.domain.Form.user.SignUpForm;

import java.util.Locale;

@Getter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@AuditOverride(forClass = BaseEntity.class)
public class Consumer extends BaseEntity{

    @Id
    @Column(name = "consumer_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;
    private String name;
    private String phone;

}
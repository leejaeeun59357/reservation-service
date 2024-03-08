package org.smarthammer.reservation.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.AuditOverride;

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
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;
    private String name;
    private String phone;
    private Boolean partnership;

}

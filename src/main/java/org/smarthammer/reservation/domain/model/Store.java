package org.smarthammer.reservation.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

import static org.hibernate.envers.RelationTargetAuditMode.NOT_AUDITED;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Audited
@AuditOverride(forClass = BaseEntity.class)
public class Store extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id", nullable = false)
    private Long store_id;

    private String store_identifier;
    private String name;
    private String description;
    private Double total_star;

    // x좌표 (위도)
    private String lat;

    // y좌표 (경도)
    private String lng;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    @Audited(targetAuditMode = NOT_AUDITED)
    @Setter
    private Manager manager;
}
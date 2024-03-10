package org.smarthammer.reservation.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import static org.hibernate.envers.RelationTargetAuditMode.NOT_AUDITED;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Audited
@AuditOverride(forClass = BaseEntity.class)
public class Review extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    private Long star;
    private String feedback;

    @OneToOne
    @JoinColumn(name = "reserve_id")
    @Audited(targetAuditMode = NOT_AUDITED)
    @Setter
    private Reserve reserve;
}

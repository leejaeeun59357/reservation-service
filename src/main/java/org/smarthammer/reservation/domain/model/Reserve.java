package org.smarthammer.reservation.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;
import org.smarthammer.reservation.domain.Status.AllowStatus;
import org.smarthammer.reservation.domain.Status.ArriveStatus;
import org.smarthammer.reservation.domain.Status.UseStatus;

import java.time.LocalDateTime;

import static org.hibernate.envers.RelationTargetAuditMode.NOT_AUDITED;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Audited
@AuditOverride(forClass = BaseEntity.class)
public class Reserve extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reserve_id", nullable = false)
    private Long id;

    private String name;

    private LocalDateTime DT;

    @Setter
    private AllowStatus allowStatus;

    @Setter
    private UseStatus useStatus;

    @Setter
    private ArriveStatus arriveStatus;

//    아직 기능 구현 전 이므로 주석 처리
//    private String allowStatus;

    @OneToOne
    @JoinColumn(name = "store_name")
    @Audited(targetAuditMode = NOT_AUDITED)
    @Setter
    private Store store;

    @ManyToOne
    @JoinColumn(name = "consumer_email")
    @Audited(targetAuditMode = NOT_AUDITED)
    @Setter
    private Consumer consumer;
}

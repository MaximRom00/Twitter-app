package by.rom.xapp.domain;

import by.rom.xapp.domain.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@Table(name = "subscriptions")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
public class Subscription extends BaseEntity<Long>{

    @OneToOne
    private User follower;

    @OneToOne
    private User following;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createdTimestamp;
}

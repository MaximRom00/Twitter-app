package by.rom.xapp.domain;

import by.rom.xapp.domain.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@Table(name = "tweets")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
public class Tweet extends BaseEntity<Long>{

    private String message;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createdTimestamp;

    @CreatedDate
    @LastModifiedDate
    @Column(nullable = false)
    private Instant updatedTimestamp;

    @ManyToOne(optional = false)
    private User user;
}

package by.rom.xapp.domain.user;

import by.rom.xapp.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "roles")
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BaseEntity<Long> {

    @Enumerated(EnumType.STRING)
    private RoleType authority;

}

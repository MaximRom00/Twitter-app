package by.rom.xapp.domain.user;

import by.rom.xapp.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "user_accounts")
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity<Long> {

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    private String imageLink;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "accounts_roles",
            joinColumns = @JoinColumn(name = "user_account_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    @Builder.Default
    private Set<Role> authorities = new HashSet<>();

}

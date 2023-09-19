package by.rom.xapp.repository;

import by.rom.xapp.domain.user.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmailAndUsername(String username, String email);

    @EntityGraph(attributePaths = "authorities")
    Optional<User> findByUsername(String userName);
}

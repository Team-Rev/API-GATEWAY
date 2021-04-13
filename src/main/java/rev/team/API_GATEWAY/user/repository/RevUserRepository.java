package rev.team.API_GATEWAY.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rev.team.API_GATEWAY.user.domain.RevUser;

import java.util.Optional;

public interface RevUserRepository extends JpaRepository<RevUser, Long> {
    Optional<RevUser> findRevUserByEmail(String email);
}

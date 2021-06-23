package rev.team.API_GATEWAY.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import rev.team.API_GATEWAY.user.domain.RevUser;

import java.util.Optional;

public interface RevUserRepository extends JpaRepository<RevUser, String> {
    Optional<RevUser> findRevUserByUserId(String id);
}

package beforespring.yourfood.auth.authmember.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ConfirmRepository extends JpaRepository<Confirm, Long> {
    Optional<Confirm> findByAuthMember(AuthMember authMember);
}

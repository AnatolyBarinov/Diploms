package iiot.istok.repository;


import iiot.istok.entity.RegisteredToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface RegisteredTokenRepository extends JpaRepository<RegisteredToken, String> {
    Optional<RegisteredToken> findFirstByLoginAndToken(String login, String token);
}
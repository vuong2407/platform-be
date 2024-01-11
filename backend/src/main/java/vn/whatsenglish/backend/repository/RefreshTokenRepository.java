package vn.whatsenglish.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.whatsenglish.backend.entity.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);
    Optional<RefreshToken> findByUserId(Integer userId);
}

package vn.whatsenglish.backend.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import vn.whatsenglish.backend.entity.RefreshToken;
import vn.whatsenglish.backend.exception.TokenRefreshException;
import vn.whatsenglish.backend.repository.RefreshTokenRepository;
import vn.whatsenglish.backend.repository.UserRepository;
import vn.whatsenglish.backend.service.impl.RefreshTokenService;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Component
public class RefreshJwtTokenService {

    @Value("${jwt.app.jwtRefreshExpirationMs}")
    private Long refreshTokenDurationMs;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RefreshTokenService refreshTokenService;

    public RefreshToken createOrUpdateRefreshToken(Integer userId) {
        Optional<RefreshToken> refreshTokenOfUser = refreshTokenRepository.findByUserId(userId);
        if (refreshTokenOfUser.isPresent()) {
            RefreshToken refreshToken = refreshTokenOfUser.get();
            refreshToken.setExpireDate(Instant.now().plusMillis(refreshTokenDurationMs));
            return refreshTokenRepository.save(refreshToken);
        } else {
            RefreshToken refreshTokenBuilder = RefreshToken.builder()
                    .expireDate(Instant.now().plusMillis(refreshTokenDurationMs))
                    .user(userRepository.findById(userId).get())
                    .token(UUID.randomUUID().toString())
                    .build();
            return refreshTokenRepository.save(refreshTokenBuilder);
        }
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpireDate().compareTo(Instant.now()) < 0) {
            refreshTokenService.deleteRefreshToken(token);
            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please login again");
        }

        return token;
    }
}

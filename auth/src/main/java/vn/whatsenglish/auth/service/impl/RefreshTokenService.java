package vn.whatsenglish.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vn.whatsenglish.auth.entity.RefreshToken;
import vn.whatsenglish.auth.repository.RefreshTokenRepository;
import vn.whatsenglish.auth.service.IRefreshTokenService;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService implements IRefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;
    @Autowired
    private UserService userService;

    @Value("${jwt.app.jwtRefreshExpirationMs}")
    private Long refreshTokenDurationMs;

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    @Override
    public RefreshToken createOrRetrieveRefreshTokenByUserId(Integer userId) {
        Optional<RefreshToken> refreshTokenOfUser = refreshTokenRepository.findByUserId(userId);
        if (refreshTokenOfUser.isPresent()) {
            return refreshTokenOfUser.get();
        } else {
            RefreshToken refreshTokenBuilder = RefreshToken.builder()
                    .expireDate(Instant.now().plusMillis(refreshTokenDurationMs))
                    .user(userService.getUserById(userId))
                    .token(UUID.randomUUID().toString())
                    .build();
            return refreshTokenRepository.save(refreshTokenBuilder);
        }
    }

    @Override
    public void deleteRefreshToken(RefreshToken token) {
        refreshTokenRepository.delete(token);
    }
}

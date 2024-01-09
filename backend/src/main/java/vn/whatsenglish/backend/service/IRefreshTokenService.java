package vn.whatsenglish.backend.service;

import vn.whatsenglish.backend.entity.RefreshToken;

import java.util.Optional;

public interface IRefreshTokenService {
    Optional<RefreshToken> findByToken(String token);
    RefreshToken createOrRetrieveRefreshTokenByUserId(Integer userId);
    void deleteRefreshToken(RefreshToken token);
}

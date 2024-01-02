package vn.whatsenglish.auth.service;

import vn.whatsenglish.auth.entity.RefreshToken;

import java.util.Optional;

public interface IRefreshTokenService {
    Optional<RefreshToken> findByToken(String token);
    RefreshToken createOrRetrieveRefreshTokenByUserId(Integer userId);
    void deleteRefreshToken(RefreshToken token);
}

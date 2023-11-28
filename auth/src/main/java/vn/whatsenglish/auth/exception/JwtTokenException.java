package vn.whatsenglish.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class JwtTokenException extends RuntimeException {

    public JwtTokenException(String token, String message) {
        super(String.format("Failed for [%s]: %s", token, message));
    }
}

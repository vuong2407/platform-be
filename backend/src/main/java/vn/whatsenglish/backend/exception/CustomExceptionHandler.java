package vn.whatsenglish.backend.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleSecurityException(Exception ex) {
        ProblemDetail errorDetail = null;
        if (ex instanceof BadCredentialsException) {
            errorDetail = ProblemDetail
                    .forStatusAndDetail(HttpStatusCode.valueOf(401), ex.getMessage());
            errorDetail.setProperty("access_denied_reason", "Authentication Failure");
        }

        if (ex instanceof AccessDeniedException) {
            errorDetail = ProblemDetail
                    .forStatusAndDetail(HttpStatusCode.valueOf(403), ex.getMessage());
            errorDetail.setProperty("access_denied_reason", "not_authorized!");

        }

        if (ex instanceof SignatureException) {
            errorDetail = ProblemDetail
                    .forStatusAndDetail(HttpStatusCode.valueOf(403), ex.getMessage());
            errorDetail.setProperty("access_denied_reason", "JWT Signature not valid");
        }

        if (ex instanceof ExpiredJwtException) {
            errorDetail = ProblemDetail
                    .forStatusAndDetail(HttpStatusCode.valueOf(403), ex.getMessage());
            errorDetail.setProperty("access_denied_reason", "JWT Token already expired !");
        }

        if (ex instanceof MalformedJwtException) {
            errorDetail = ProblemDetail
                    .forStatusAndDetail(HttpStatusCode.valueOf(403), ex.getMessage());
            errorDetail.setProperty("access_denied_reason", "JWT Token already is invalid !");
        }
        return errorDetail;
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ProblemDetail handleNoDataException(NoDataFoundException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(400), ex.getMessage());
    }

    @ExceptionHandler(BadGatewayException.class)
    public ProblemDetail handleBadGatewayException(BadGatewayException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(502), ex.getMessage());
    }
}

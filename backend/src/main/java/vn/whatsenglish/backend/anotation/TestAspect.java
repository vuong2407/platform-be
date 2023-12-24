package vn.whatsenglish.backend.anotation;

import com.google.gson.Gson;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import vn.whatsenglish.domain.dto.product.ResponseBodyDto;

@Component
@Aspect
public class TestAspect {

    @Around("@annotation(Test)")
    public ResponseEntity<?> response(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Input :\n" + joinPoint.getArgs()[0]);

//        HttpServletRequest servletRequest = (HttpServletRequest) joinPoint.getArgs()[1];
//        System.out.println(servletRequest.getRemoteAddr());

        ResponseEntity<?> result = (ResponseEntity<?>) joinPoint.proceed();
        if (result.getStatusCode().isError()) {
            return new ResponseEntity<>(result.getBody(), HttpStatusCode.valueOf(result.getStatusCode().value()));
        }

        System.out.println("test");

        return result;
    }
}

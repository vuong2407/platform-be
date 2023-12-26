package vn.whatsenglish.order.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import vn.whatsenglish.domain.exception.BadRequestException;
import vn.whatsenglish.domain.exception.NotFoundException;

@RestController
public class BaseController {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> badRequestExceptionHandler(BadRequestException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> notFoundExceptionHandler(NotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}

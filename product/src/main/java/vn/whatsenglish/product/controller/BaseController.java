package vn.whatsenglish.product.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import vn.whatsenglish.product.exception.BadRequestException;
import vn.whatsenglish.product.exception.NotFoundException;
import vn.whatsenglish.product.util.ResponseHandler;

@RestController
public class BaseController {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> badRequestExceptionHandler(BadRequestException e) {
        return ResponseHandler.buildCustomResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> notFoundExceptionHandler(NotFoundException e) {
        return ResponseHandler.buildCustomResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }
}

package vn.whatsenglish.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Response;

import java.io.IOException;

@RestController
public class BaseController {
    protected ResponseEntity<?> handleResponse(Response<?> response) {
        try {
            if (!response.isSuccessful()) {
                assert response.errorBody() != null;
                return new ResponseEntity<>(response.errorBody().string(), HttpStatusCode.valueOf(response.raw().code()));
            }
            return ResponseEntity.ok(response.body());
        } catch (IOException e) {
            return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
        }
    }
}

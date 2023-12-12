package vn.whatsenglish.product.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import vn.whatsenglish.product.dto.response.ResponseBodyDTO;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ResponseHandler {

    public static ResponseEntity<String> buildCustomResponse(int code, String message) {
        ResponseBodyDTO responseBodyDTO = new ResponseBodyDTO(message, Timestamp.valueOf(LocalDateTime.now()).getTime());
        return ResponseEntity.status(HttpStatus.valueOf(code)).body(JsonUtil.toJsonString(responseBodyDTO));
    }
}

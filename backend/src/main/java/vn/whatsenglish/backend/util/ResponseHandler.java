package vn.whatsenglish.backend.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import vn.whatsenglish.backend.dto.ResponseBodyDto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ResponseHandler {

    public static ResponseEntity<String> buildCustomResponse(int code, String message) {
        ResponseBodyDto responseBodyDTO = new ResponseBodyDto(message, Timestamp.valueOf(LocalDateTime.now()).getTime());
        return ResponseEntity.status(HttpStatus.valueOf(code)).body(JsonUtil.toJsonString(responseBodyDTO));
    }
}

package vn.whatsenglish.backend.controller;

import io.grpc.StatusRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import vn.whatsenglish.backend.enums.MappingStatusCode;
import vn.whatsenglish.backend.util.ResponseHandler;

import java.util.HashMap;
import java.util.Map;

@RestController
public class BaseController {

    public static final Map<String, Integer> mappingStatus = new HashMap<>();

    static {
        for (MappingStatusCode code : MappingStatusCode.values()) {
            mappingStatus.put(code.name(), code.getValue());
        }
    }

    @ExceptionHandler(StatusRuntimeException.class)
    public ResponseEntity<String> badRequestExceptionHandler(StatusRuntimeException e) {
        Integer code = mappingStatus.get(e.getStatus().getCode().name());
        return ResponseHandler.buildCustomResponse(code != null ? code : mappingStatus.get("UNKNOWN"), e.getStatus().getDescription());
    }
}

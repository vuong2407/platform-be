package vn.whatsenglish.product.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import vn.whatsenglish.product.exception.BadRequestException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonUtil {

    public static String toJsonString(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public static <T> T toJavaObject(byte[] bytes, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(bytes, clazz);
        } catch (IOException e) {
            throw new BadRequestException(e.getMessage());
        }
    }


    public static <T> List<T> toListObject(String json, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz));
        } catch (JsonProcessingException e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}

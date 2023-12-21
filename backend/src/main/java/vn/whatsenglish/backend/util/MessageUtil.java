package vn.whatsenglish.backend.util;

import com.google.protobuf.Message;
import com.googlecode.protobuf.format.JsonFormat;

public class MessageUtil {

    public static <T> T convertMessageToDto(Message message, Class<T> clazz) {
        String jsonString = new JsonFormat().printToString(message);
        return JsonUtil.toJavaObject(jsonString, clazz);
    }
}

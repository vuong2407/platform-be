package vn.whatsenglish.product.util.dto;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.util.JsonFormat;
import vn.whatsenglish.product.exception.BadRequestException;

public class GrpcUtil {

    public static String covertMessageToString(MessageOrBuilder messageOrBuilder) {
        try {
            return JsonFormat.printer().print(messageOrBuilder);
        } catch (InvalidProtocolBufferException e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}

package vn.whatsenglish.product.util;

import org.springframework.util.StringUtils;
import vn.whatsenglish.product.constant.Messages;
import vn.whatsenglish.product.exception.BadRequestException;

import java.text.MessageFormat;
import java.util.Objects;

public class ObjectsUtil {

    public static void checkRequiredParameters(Object object, String parameter) {
        if (Objects.isNull(object)
            || (object instanceof String && !StringUtils.hasText(object.toString()))) {
            throw new BadRequestException(MessageFormat.format(Messages.MISSING_REQUIRED_PARAMETER, parameter));
        }
    }
}

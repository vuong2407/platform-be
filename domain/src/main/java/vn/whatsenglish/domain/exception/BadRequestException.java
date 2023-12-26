package vn.whatsenglish.domain.exception;

import vn.whatsenglish.domain.dto.product.constants.Messages;

public class BadRequestException extends RuntimeException {

    public BadRequestException() {
        super(Messages.INVALID_REQUEST);
    }

    public BadRequestException(String message) {
        super(message);
    }
}

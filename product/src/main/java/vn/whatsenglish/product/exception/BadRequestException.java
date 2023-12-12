package vn.whatsenglish.product.exception;

import vn.whatsenglish.product.constant.Messages;

public class BadRequestException extends BaseProductException {

    public BadRequestException() {
        super(Messages.INVALID_REQUEST);
    }

    public BadRequestException(String message) {
        super(message);
    }
}

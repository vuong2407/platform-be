package vn.whatsenglish.domain.exception;

import vn.whatsenglish.domain.dto.product.constants.Messages;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super(Messages.DATA_IS_NOT_FOUND);
    }

    public NotFoundException(String message) {
        super(message);
    }
}

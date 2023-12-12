package vn.whatsenglish.product.exception;

import vn.whatsenglish.product.constant.Messages;

public class NotFoundException extends BaseProductException {

    public NotFoundException() {
        super(Messages.DATA_IS_NOT_FOUND);
    }

    public NotFoundException(String message) {
        super(message);
    }
}

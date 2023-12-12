package vn.whatsenglish.product.exception;

public class BaseProductException extends RuntimeException {

    public BaseProductException() {}

    public BaseProductException(String message) {
        super(message);
    }

    public BaseProductException(Throwable cause) {
        super(cause);
    }

    public BaseProductException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseProductException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

package vn.whatsenglish.backend.enums;

public enum MappingStatusCode {
    NOT_FOUND(400),
    UNKNOWN(503),
    INVALID_ARGUMENT(400),
    DEADLINE_EXCEEDED(408),
    ALREADY_EXISTS(400),
    PERMISSION_DENIED(401);

    private int value;

    MappingStatusCode (int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

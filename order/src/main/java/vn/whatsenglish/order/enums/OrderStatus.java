package vn.whatsenglish.order.enums;

public enum OrderStatus {
    PAID(0, "The order is paid and waiting for delivery"),
    CHECKOUT(1, "The order is checked out and waiting for paid it"),
    CANCELED(2, "The order is canceled"),
    FAIL(3, "The order fail with some errors");

    private final int statusCode;
    private final String description;

    OrderStatus(int statusCode, String description) {
        this.statusCode = statusCode;
        this.description = description;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getDescription() {
        return description;
    }
}

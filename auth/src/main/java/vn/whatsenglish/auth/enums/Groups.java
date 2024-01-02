package vn.whatsenglish.auth.enums;

import lombok.Getter;

public enum Groups {
    CUSTOMER("customer", "CUSTOMER"),
    ADMIN("admin", "ADMIN");

    @Getter
    private final String groupCode;

    @Getter
    private final String groupName;

    Groups(String groupCode, String groupName) {
        this.groupCode = groupCode;
        this.groupName = groupName;
    }

}

package vn.whatsenglish.backend.enums;

import lombok.Getter;

@Getter
public enum Groups {
    CUSTOMER("customer", "CUSTOMER"),
    ADMIN("admin", "ADMIN");

    private final String groupCode;

    private final String groupName;

    Groups(String groupCode, String groupName) {
        this.groupCode = groupCode;
        this.groupName = groupName;
    }

}

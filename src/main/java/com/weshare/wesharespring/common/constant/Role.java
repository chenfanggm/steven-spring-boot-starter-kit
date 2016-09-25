package com.weshare.wesharespring.common.constant;

public enum Role {

    USER("ROLE_USER", 1),
    ADMIN("ROLE_ADMIN", 2),
    USER_AND_ADMIN("ROLE_USER, ROLE_ADMIN", 3);

    private final String name;
    private final Integer value;

    private Role(final String name, final Integer value) {
        this.name = name;
        this.value = value;
    }

    public boolean equalsName(final String otherName) {
        return otherName != null && name.equals(otherName);
    }

    public Integer getValue() {
        return this.value;
    }

    public String toString() {
        return this.name;
    }

}

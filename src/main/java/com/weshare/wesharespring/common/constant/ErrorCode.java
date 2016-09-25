package com.weshare.wesharespring.common.constant;

public enum  ErrorCode {

    /**
     * Use: error code instead of error message
     * When: the message itself should be hidden from client
     */
    ITEM_NOT_FOUND("Item not found!", 101);

    private final String message;
    private final Integer code;

    private ErrorCode(final String errorMessage, final Integer code) {
        this.message = errorMessage;
        this.code = code;
    }

    public boolean equals(final Integer otherCode) {
        return otherCode != null && code.equals(otherCode);
    }

    public String toString() {
        return code.toString();
    }

    public String getMessage() {
        return message;
    }
}

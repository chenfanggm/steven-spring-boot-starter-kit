package com.weshare.wesharespring.entity.Response;

import java.io.Serializable;

public class SuccessResponse implements Serializable {

    private String message = "Action has been executed successfully";

    public SuccessResponse() {
    }

    public SuccessResponse(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }
}

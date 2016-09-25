package com.weshare.wesharespring.entity.Response;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class ErrorResponse {

    private Long timestamp;
    private String message;
    private String path;

    public ErrorResponse() { }

    public ErrorResponse(final String message, final HttpServletRequest request) {
        this.timestamp = new Date().getTime();
        this.message = message;
        this.path = request.getRequestURI();
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(final String path) {
        this.path = path;
    }
}

package com.weshare.wesharespring.common.exception;

import com.weshare.wesharespring.common.constant.ErrorCode;

public class BadRequestException extends BaseServiceException {

    public BadRequestException() {

        super();
    }

    public BadRequestException(final String error) {

        super(error);
    }

    public BadRequestException(final Throwable cause) {

        super(cause);
    }

    public BadRequestException(final String error, final Throwable cause) {

        super(error, cause);
    }

    public BadRequestException(final ErrorCode errorCode) {

        super(errorCode);
    }

    public BadRequestException(final String error, final ErrorCode errorCode) {

        super(error, errorCode);
    }
}

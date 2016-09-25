package com.weshare.wesharespring.common.exception;

import com.weshare.wesharespring.common.constant.ErrorCode;

public class LogicalErrorException extends BaseServiceException {

    public LogicalErrorException() {

        super();
    }

    public LogicalErrorException(final String error) {

        super(error);
    }

    public LogicalErrorException(final Throwable cause) {

        super(cause);
    }

    public LogicalErrorException(final String error, final Throwable cause) {

        super(error, cause);
    }

    public LogicalErrorException(final ErrorCode errorCode) {

        super(errorCode);
    }

    public LogicalErrorException(final String error, final ErrorCode errorCode) {

        super(error, errorCode);
    }
}

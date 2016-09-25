package com.weshare.wesharespring.common.exception;

import com.weshare.wesharespring.common.constant.ErrorCode;

public class PreConditionFailedException extends BaseServiceException {

    public PreConditionFailedException() {

        super();
    }

    public PreConditionFailedException(final String error) {

        super(error);
    }

    public PreConditionFailedException(final Throwable cause) {

        super(cause);
    }

    public PreConditionFailedException(final String error, final Throwable cause) {

        super(error, cause);
    }

    public PreConditionFailedException(final ErrorCode errorCode) {

        super(errorCode);
    }

    public PreConditionFailedException(final String error, final ErrorCode errorCode) {

        super(error, errorCode);
    }
}

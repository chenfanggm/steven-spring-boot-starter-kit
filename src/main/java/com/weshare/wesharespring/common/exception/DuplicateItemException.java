package com.weshare.wesharespring.common.exception;

import com.weshare.wesharespring.common.constant.ErrorCode;

public class DuplicateItemException extends BaseServiceException {

    public DuplicateItemException() {

        super();
    }

    public DuplicateItemException(final String error) {

        super(error);
    }

    public DuplicateItemException(final Throwable cause) {

        super(cause);
    }

    public DuplicateItemException(final String error, final Throwable cause) {

        super(error, cause);
    }

    public DuplicateItemException(final ErrorCode errorCode) {

        super(errorCode);
    }

    public DuplicateItemException(final String error, final ErrorCode errorCode) {

        super(error, errorCode);
    }
}

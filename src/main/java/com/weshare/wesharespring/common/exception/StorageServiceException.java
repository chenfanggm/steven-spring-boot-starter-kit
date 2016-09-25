package com.weshare.wesharespring.common.exception;

import com.weshare.wesharespring.common.constant.ErrorCode;

public class StorageServiceException extends BaseServiceException {

    public StorageServiceException() {

        super();
    }

    public StorageServiceException(final String error) {

        super(error);
    }

    public StorageServiceException(final Throwable cause) {

        super(cause);
    }

    public StorageServiceException(final String error, final Throwable cause) {

        super(error, cause);
    }

    public StorageServiceException(final ErrorCode errorCode) {

        super(errorCode);
    }

    public StorageServiceException(final String error, final ErrorCode errorCode) {

        super(error, errorCode);
    }
}

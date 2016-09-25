package com.weshare.wesharespring.common.exception;

import com.weshare.wesharespring.common.constant.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidInputException extends BaseServiceException {

    public InvalidInputException() {

        super();
    }

    public InvalidInputException(final String error) {

        super(error);
    }

    public InvalidInputException(final Throwable cause) {

        super(cause);
    }

    public InvalidInputException(final String error, final Throwable cause) {

        super(error, cause);
    }

    public InvalidInputException(final ErrorCode errorCode) {

        super(errorCode);
    }

    public InvalidInputException(final String error, final ErrorCode errorCode) {

        super(error, errorCode);
    }
}

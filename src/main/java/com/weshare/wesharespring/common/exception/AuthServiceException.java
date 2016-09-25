package com.weshare.wesharespring.common.exception;

import org.springframework.security.core.AuthenticationException;

public class AuthServiceException extends AuthenticationException {

    public AuthServiceException() {

        super("Unable to authenticate User with provided credentials");
    }

    public AuthServiceException(final String error) {

        super(error);
    }

    public AuthServiceException(final Throwable cause) {

        super("Unable to authenticate User with provided credentials", cause);
    }

    public AuthServiceException(final String error, final Throwable cause) {

        super(error, cause);
    }
}

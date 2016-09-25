package com.weshare.wesharespring.common.exception;


import com.weshare.wesharespring.common.constant.Constant;
import com.weshare.wesharespring.entity.Response.ErrorResponse;
import com.weshare.wesharespring.common.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(AuthServiceException.class)
    public ResponseEntity<ErrorResponse> authServiceExceptionHandler(final HttpServletRequest request, final HttpServletResponse response, final AuthServiceException exception) {
        logger.error("<Exception> Request: " + request.getRequestURI() + " AuthServiceException: " + exception.toString());
        Utils.addCookie(response, Constant.AUTH_JWT_TOKEN_COOKIE, "", 0);
        Utils.addCookie(response, Constant.AUTH_JWT_REFRESH_TOKEN_COOKIE, "", 0);
        SecurityContextHolder.clearContext();
        return ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body(new ErrorResponse("Unable to authenticate User with provided credentials", request));
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<ErrorResponse> invalidInputExceptionHandler(final HttpServletRequest request, final InvalidInputException exception) {
        logger.error("<Exception> Request: " + request.getRequestURI() + " InvalidInputException: " + exception.toString());
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(new ErrorResponse("Invalid Input Arguments", request));
    }

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<ErrorResponse> itemNotFoundExceptionHandler(final HttpServletRequest request, final ItemNotFoundException exception) {
        logger.error("<Exception> Request: " + request.getRequestURL() + " ItemNotFoundException: " + exception.toString());
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(new ErrorResponse("Item Not Found", request));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> badRequestExceptionHandler(final HttpServletRequest request, final BadRequestException exception) {
        logger.error("<Exception> Request: " + request.getRequestURI() + " BadRequestException: " + exception.toString());
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(new ErrorResponse(exception.getMessage(), request));
    }

    @ExceptionHandler(PreConditionFailedException.class)
    public ResponseEntity<ErrorResponse> badRequestExceptionHandler(final HttpServletRequest request, final PreConditionFailedException exception) {
        logger.error("<Exception> Request: " + request.getRequestURI() + " PreConditionFailedException: " + exception.toString());
        return ResponseEntity
            .status(HttpStatus.PRECONDITION_FAILED)
            .body(new ErrorResponse("Pre-Condition Failed", request));
    }

    @ExceptionHandler(DuplicateItemException.class)
    public ResponseEntity<ErrorResponse> duplicateItemExceptionHandler(final HttpServletRequest request, final DuplicateItemException exception) {
        logger.error("<Exception> Request: " + request.getRequestURI() + " DuplicateItemException: " + exception.toString());
        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(new ErrorResponse("Duplicate Item Already Exist", request));
    }

    @ExceptionHandler(LogicalErrorException.class)
    public ResponseEntity<ErrorResponse> logicalErrorExceptionHandler(final HttpServletRequest request, final LogicalErrorException exception) {
        logger.error("<Exception> Request: " + request.getRequestURI() + " LogicalErrorException: " + exception.toString());
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ErrorResponse("Internal Logical Error", request));
    }

    @ExceptionHandler(StorageServiceException.class)
    public ResponseEntity<ErrorResponse> storageServiceExceptionHandler(final HttpServletRequest request, final StorageServiceException exception) {
        logger.error("<Exception> Request: " + request.getRequestURI() + " StorageServiceException: " + exception.toString());
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ErrorResponse("Internal Storage Service Error", request));
    }

    // generic exception handler
    /*
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> unhandledErrorHandler(final HttpServletRequest request, final Exception exception) {
        logger.error("Request: " + request.getRequestURI() + " UnhandledException " + exception.toString());
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ErrorResponse("Internal Unhandled Exception", request));
    }
    */
}


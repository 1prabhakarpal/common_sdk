package com.common.sdk.common_sdk.exception;

import java.util.stream.Collectors;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.common.sdk.common_sdk.models.enums.APIResponseCode;
import com.common.sdk.common_sdk.models.interfaces.GenericApiResponse;
import com.common.sdk.common_sdk.services.ResponseHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author PRABHAKAR PAL
 * @version 1.0
 * @since 2025-11-26
 * @implNote This class is used to handle global exceptions in the application.
 */

@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class BaseGlobalExceptionHandler {

    private final ResponseHandler responseHandler;

    /**
     * @implNote This method is used to handle global exceptions in the application.
     * @param e
     * @return GenericApiResponse<Void>
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BaseException.class)
    public GenericApiResponse<Void> handleException(BaseException e) {
        log.error("Exception occured : code = {}, message = {}, error = {}", e.getStatusCode(), e.getMessage(),
                e.getStackTrace());
        return responseHandler.failure(e.getStatusCode(), e.getMessage());
    }

    /**
     * @implNote This method is used to handle validation exceptions in the
     *           application.
     * @param ex
     * @return GenericApiResponse<Void>
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GenericApiResponse<Void> handleValidation(MethodArgumentNotValidException ex) {
        String errorMsg = ex.getBindingResult().getFieldErrors()
                .stream().map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.joining(", "));
        log.warn("Validation error: {} - {}", ex.getClass().getSimpleName(), errorMsg);
        return responseHandler.failure(APIResponseCode.VALIDATION_ERROR.getCode(), errorMsg);
    }

    /**
     * @implNote This method is used to handle missing request parameter exceptions
     *           in the application.
     * @param ex
     * @return GenericApiResponse<Void>
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GenericApiResponse<Void> handleMissingParam(MissingServletRequestParameterException ex) {
        log.warn("Missing request parameter: {} - {}", ex.getClass().getSimpleName(), ex.getMessage());
        return responseHandler.failure(APIResponseCode.MISSING_REQUEST_PARAM.getCode(), ex.getMessage());
    }

    /**
     * @implNote This method is used to handle missing path variable exceptions
     *           in the application.
     * @param ex
     * @return GenericApiResponse<Void>
     */
    @ExceptionHandler(MissingPathVariableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GenericApiResponse<Void> handleMissingPathVar(MissingPathVariableException ex) {
        log.warn("Missing path variable: {} - {}", ex.getClass().getSimpleName(), ex.getMessage());
        return responseHandler.failure(APIResponseCode.MISSING_PATH_VARIABLE.getCode(), ex.getMessage());
    }

    /**
     * @implNote This method is used to handle missing request header exceptions
     *           in the application.
     * @param ex
     * @return GenericApiResponse<Void>
     */
    @ExceptionHandler(MissingRequestHeaderException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GenericApiResponse<Void> handleMissingHeader(MissingRequestHeaderException ex) {
        log.warn("Missing request header: {} - {}", ex.getClass().getSimpleName(), ex.getMessage());
        return responseHandler.failure(APIResponseCode.MISSING_REQUEST_HEADER.getCode(), ex.getMessage());
    }

    /**
     * @implNote This method is used to handle method argument type mismatch
     *           exceptions
     *           in the application.
     * @param ex
     * @return GenericApiResponse<Void>
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GenericApiResponse<Void> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        log.warn("Argument type mismatch: {} - {}", ex.getClass().getSimpleName(), ex.getMessage());
        return responseHandler.failure(APIResponseCode.ARGUMENT_TYPE_MISMATCH.getCode(), ex.getMessage());
    }

    /**
     * @implNote This method is used to handle http message not readable exceptions
     *           in the application.
     * @param ex
     * @return GenericApiResponse<Void>
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GenericApiResponse<Void> handleUnreadable(HttpMessageNotReadableException ex) {
        log.warn("Message not readable: {} - {}", ex.getClass().getSimpleName(), ex.getMessage());
        return responseHandler.failure(APIResponseCode.HTTP_MESSAGE_NOT_READABLE.getCode(),
                "Invalid request payload or format");
    }

    /**
     * @implNote This method is used to handle conversion failed exceptions
     *           in the application.
     * @param ex
     * @return GenericApiResponse<Void>
     */
    @ExceptionHandler(ConversionFailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GenericApiResponse<Void> handleConversion(ConversionFailedException ex) {
        log.warn("Conversion failed: {} - {}", ex.getClass().getSimpleName(), ex.getMessage());
        return responseHandler.failure(APIResponseCode.CONVERSION_FAILED.getCode(), ex.getMessage());
    }

    /**
     * @implNote This method is used to handle http request method not supported
     *           exceptions
     *           in the application.
     * @param ex
     * @return GenericApiResponse<Void>
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public GenericApiResponse<Void> handleMethodNotAllowed(HttpRequestMethodNotSupportedException ex) {
        log.warn("Method not allowed: {} - {}", ex.getClass().getSimpleName(), ex.getMessage());
        return responseHandler.failure(APIResponseCode.METHOD_NOT_ALLOWED);
    }

    /**
     * @implNote This method is used to handle http media type not supported
     *           exceptions
     *           in the application.
     * @param ex
     * @return GenericApiResponse<Void>
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public GenericApiResponse<Void> handleUnsupportedMedia(HttpMediaTypeNotSupportedException ex) {
        log.warn("Unsupported media type: {} - {}", ex.getClass().getSimpleName(), ex.getMessage());
        return responseHandler.failure(APIResponseCode.UNSUPPORTED_MEDIA_TYPE);
    }

    /**
     * @implNote This method is used to handle illegal argument exceptions
     *           in the application.
     * @param ex
     * @return GenericApiResponse<Void>
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public GenericApiResponse<Void> handleIllegalArgument(IllegalArgumentException ex) {
        log.error("Illegal argument: {} - {}", ex.getClass().getSimpleName(), ex.getMessage());
        return responseHandler.failure(APIResponseCode.ILLEGAL_ARGUMENT.getCode(), ex.getMessage());
    }

    /**
     * @implNote This method is used to handle illegal state exceptions
     *           in the application.
     * @param ex
     * @return GenericApiResponse<Void>
     */
    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public GenericApiResponse<Void> handleIllegalState(IllegalStateException ex) {
        log.error("Illegal state: {} - {}", ex.getClass().getSimpleName(), ex.getMessage());
        return responseHandler.failure(APIResponseCode.ILLEGAL_STATE.getCode(), ex.getMessage());
    }

    /**
     * @implNote This method is used to handle null pointer exceptions
     *           in the application.
     * @param ex
     * @return GenericApiResponse<Void>
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public GenericApiResponse<Void> handleNullPointer(NullPointerException ex) {
        log.error("Null pointer: {} - {}", ex.getClass().getSimpleName(), ex.getMessage());
        return responseHandler.failure(APIResponseCode.NULL_POINTER.getCode(),
                "Unexpected null value encountered");
    }

    // --- Fallback handler for uncaught exceptions ---
    /**
     * @implNote This method is used to handle uncaught exceptions
     *           in the application.
     * @param ex
     * @return GenericApiResponse<Void>
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public GenericApiResponse<Void> handleGeneric(Exception ex) {
        log.error("Unhandled exception: {} - {}", ex.getClass().getSimpleName(), ex.getMessage(), ex);
        return responseHandler.failure(APIResponseCode.INTERNAL_ERROR.getCode(),
                "Sorry something went wrong on server side, please try again after sometime.");
    }

}

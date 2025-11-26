package com.common.sdk.common_sdk.exception;

import lombok.Getter;

/**
 * @author PRABHAKAR PAL
 * @version 1.0
 * @since 2025-11-25
 * @implNote This class is used to throw custom exceptions in the application.
 */
@Getter
public class BaseException extends RuntimeException {
    private final int statusCode;
    private final String message;

    /**
     * @implNote This constructor is used to throw custom exceptions in the
     *           application.
     * @param statusCode
     * @param message
     */
    public BaseException(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}

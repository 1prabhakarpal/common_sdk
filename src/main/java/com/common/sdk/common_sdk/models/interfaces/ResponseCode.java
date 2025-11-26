package com.common.sdk.common_sdk.models.interfaces;

/**
 * @author PRABHAKAR PAL
 * @version 1.0
 * @since 2025-11-25
 * @implNote This interface is used to store response codes in the application.
 */
public interface ResponseCode {

    /**
     * @implNote This method is used to get the code from the response.
     * @return int
     */
    int getCode();

    /**
     * @implNote This method is used to get the message from the response.
     * @return String
     */
    String getMessage();
}

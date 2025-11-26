package com.common.sdk.common_sdk.models.dto;

import com.common.sdk.common_sdk.constants.ApplicationConstants;
import com.common.sdk.common_sdk.models.interfaces.GenericApiResponse;
import com.common.sdk.common_sdk.models.interfaces.ResponseCode;

import lombok.Getter;

/**
 * @author PRABHAKAR PAL
 * @version 1.0
 * @since 2025-11-25
 * @implNote This class is used to store api response in the application.
 */
@Getter
public class ApiResponse<R> implements GenericApiResponse<R>, ApplicationConstants {

    private boolean success;
    private R data;
    private Integer code;
    private String errorMessage;
    private String requestId;

    private ApiResponse() {
    }

    /**
     * @implNote This method is used to create success response.
     * @param data
     * @param responseCode
     * @param requestId
     * @return ApiResponse<R>
     */
    public static <R> ApiResponse<R> success(R data, ResponseCode responseCode, String requestId) {
        ApiResponse<R> response = new ApiResponse<>();
        response.success = true;
        response.data = data;
        response.code = responseCode.getCode();
        response.requestId = requestId;
        return response;
    }

    /**
     * @implNote This method is used to create success response.
     * @param responseCode
     * @param requestId
     * @return ApiResponse<R>
     */
    public static <R> ApiResponse<R> success(ResponseCode responseCode, String requestId) {
        return success(null, responseCode, requestId);
    }

    /**
     * @implNote This method is used to create failure response.
     * @param responseCode
     * @param message
     * @param requestId
     * @return ApiResponse<R>
     */
    public static <R> ApiResponse<R> failure(ResponseCode responseCode, String message, String requestId) {
        ApiResponse<R> response = new ApiResponse<>();
        response.success = false;
        response.code = responseCode.getCode();
        response.errorMessage = message;
        response.requestId = requestId;
        return response;
    }

    /**
     * @implNote This method is used to create failure response.
     * @param responseCode
     * @param requestId
     * @return ApiResponse<R>
     */
    public static <R> ApiResponse<R> failure(ResponseCode responseCode, String requestId) {
        return failure(responseCode, EMPTY_STRING, requestId);
    }

    /**
     * @implNote This method is used to create failure response.
     * @param code
     * @param message
     * @param requestId
     * @return ApiResponse<R>
     */
    public static <R> ApiResponse<R> failure(int code, String message, String requestId) {
        ApiResponse<R> response = new ApiResponse<>();
        response.success = false;
        response.code = code;
        response.errorMessage = message;
        response.requestId = requestId;
        return response;
    }
}

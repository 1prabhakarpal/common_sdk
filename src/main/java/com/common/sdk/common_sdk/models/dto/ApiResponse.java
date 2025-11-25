package com.common.sdk.common_sdk.models.dto;

import com.common.sdk.common_sdk.constants.ApplicationConstants;
import com.common.sdk.common_sdk.models.interfaces.GenericApiResponse;
import com.common.sdk.common_sdk.models.interfaces.ResponseCode;

import lombok.Getter;

@Getter
public class ApiResponse<R> implements GenericApiResponse<R>, ApplicationConstants {

    private boolean success;
    private R data;
    private Integer code;
    private String errorMessage;
    private String requestId;

    private ApiResponse() {
    }

    public static <R> ApiResponse<R> success(R data, ResponseCode responseCode, String requestId) {
        ApiResponse<R> response = new ApiResponse<>();
        response.success = true;
        response.data = data;
        response.code = responseCode.getCode();
        response.requestId = requestId;
        return response;
    }

    public static <R> ApiResponse<R> success(ResponseCode responseCode, String requestId) {
        return success(null, responseCode, requestId);
    }

    public static <R> ApiResponse<R> failure(ResponseCode responseCode, String message, String requestId) {
        ApiResponse<R> response = new ApiResponse<>();
        response.success = false;
        response.code = responseCode.getCode();
        response.errorMessage = message;
        response.requestId = requestId;
        return response;
    }

    public static <R> ApiResponse<R> failure(ResponseCode responseCode, String requestId) {
        return failure(responseCode, EMPTY_STRING, requestId);
    }
}

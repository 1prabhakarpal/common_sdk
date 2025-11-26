package com.common.sdk.common_sdk.services;

import org.springframework.stereotype.Component;

import com.common.sdk.common_sdk.models.dto.ApiResponse;
import com.common.sdk.common_sdk.models.enums.APIResponseCode;
import com.common.sdk.common_sdk.models.interfaces.GenericApiResponse;
import com.common.sdk.common_sdk.models.interfaces.ResponseCode;
import com.common.sdk.common_sdk.utils.CommonUtils;

/**
 * @author PRABHAKAR PAL
 * @version 1.0
 * @since 2025-11-26
 * @implNote This class is used to handle responses in the application.
 */
@Component
public class ResponseHandler {

    /**
     * @implNote This method is used to create success response.
     * @param data
     * @return GenericApiResponse<T>
     */
    public <T> GenericApiResponse<T> ok(T data) {
        return ApiResponse.success(data, APIResponseCode.SUCCESS, CommonUtils.getCurrentRequestId());
    }

    /**
     * @implNote This method is used to create success response.
     * @return GenericApiResponse<T>
     */
    public <T> GenericApiResponse<T> ok() {
        return ApiResponse.success(APIResponseCode.SUCCESS, CommonUtils.getCurrentRequestId());
    }

    /**
     * @implNote This method is used to create success response.
     * @param data
     * @param responseCode
     * @return GenericApiResponse<T>
     */
    public <T> GenericApiResponse<T> ok(T data, ResponseCode responseCode) {
        return ApiResponse.success(data, responseCode, CommonUtils.getCurrentRequestId());
    }

    /**
     * @implNote This method is used to create success response.
     * @param responseCode
     * @return GenericApiResponse<T>
     */
    public GenericApiResponse<Void> ok(ResponseCode responseCode) {
        return ApiResponse.success(responseCode, CommonUtils.getCurrentRequestId());
    }

    /**
     * @implNote This method is used to create failure response.
     * @param responseCode
     * @return GenericApiResponse<Void>
     */
    public GenericApiResponse<Void> failure(ResponseCode responseCode) {
        return ApiResponse.failure(responseCode, CommonUtils.getCurrentRequestId());
    }

    /**
     * @implNote This method is used to create failure response.
     * @param code
     * @param message
     * @return GenericApiResponse<Void>
     */
    public GenericApiResponse<Void> failure(int code, String message) {
        return ApiResponse.failure(code, message, CommonUtils.getCurrentRequestId());
    }

    /**
     * @implNote This method is used to create failure response.
     * @param responseCode
     * @param message
     * @param requestId
     * @return GenericApiResponse<Void>
     */
    public GenericApiResponse<Void> failure(ResponseCode responseCode, String message, String requestId) {
        return ApiResponse.failure(responseCode, message, requestId);
    }
}

package com.common.sdk.common_sdk.services;

import org.springframework.stereotype.Component;

import com.common.sdk.common_sdk.models.dto.ApiResponse;
import com.common.sdk.common_sdk.models.enums.APIResponseCode;
import com.common.sdk.common_sdk.models.interfaces.GenericApiResponse;
import com.common.sdk.common_sdk.models.interfaces.ResponseCode;
import com.common.sdk.common_sdk.utils.CommonUtils;

@Component
public class ResponseHandler {

    public <T> GenericApiResponse<T> ok(T data) {
        return ApiResponse.success(data, APIResponseCode.SUCCESS, CommonUtils.getCurrentRequestId());
    }

    public <T> GenericApiResponse<T> ok() {
        return ApiResponse.success(APIResponseCode.SUCCESS, CommonUtils.getCurrentRequestId());
    }

    public <T> GenericApiResponse<T> ok(T data, ResponseCode responseCode) {
        return ApiResponse.success(data, responseCode, CommonUtils.getCurrentRequestId());
    }

    public GenericApiResponse<Void> ok(ResponseCode responseCode) {
        return ApiResponse.success(responseCode, CommonUtils.getCurrentRequestId());
    }

    public GenericApiResponse<Void> failure(ResponseCode responseCode) {
        return ApiResponse.failure(responseCode, CommonUtils.getCurrentRequestId());
    }

    public GenericApiResponse<Void> failure(int code, String message) {
        return ApiResponse.failure(code, message, CommonUtils.getCurrentRequestId());
    }

    public GenericApiResponse<Void> failure(ResponseCode responseCode, String message, String requestId) {
        return ApiResponse.failure(responseCode, message, requestId);
    }
}

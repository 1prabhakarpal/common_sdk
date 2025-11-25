package com.common.sdk.common_sdk.models.dto;

import com.common.sdk.common_sdk.models.interfaces.GenericApiResponse;

import lombok.Getter;

@Getter
public class ApiResponse<R> implements GenericApiResponse<R> {

    private boolean success;
    private R data;
    private Integer code;
    private String errorMessage;
    private String requestId;

    private ApiResponse() {
    }

}

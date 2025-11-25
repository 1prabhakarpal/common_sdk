package com.common.sdk.common_sdk.models.interfaces;

public interface GenericApiResponse<R> {
    boolean isSuccess();

    R getData();

    Integer getCode();

    String getErrorMessage();
}

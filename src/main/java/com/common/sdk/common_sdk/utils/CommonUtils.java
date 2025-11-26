package com.common.sdk.common_sdk.utils;

import org.slf4j.MDC;

import com.common.sdk.common_sdk.constants.ApplicationConstants;

public final class CommonUtils {

    private CommonUtils() {
    }

    public static String getCurrentRequestId() {
        return MDC.get(ApplicationConstants.CORRELATION_ID);
    }

}

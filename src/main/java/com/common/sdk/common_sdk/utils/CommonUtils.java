package com.common.sdk.common_sdk.utils;

import org.slf4j.MDC;

import com.common.sdk.common_sdk.constants.ApplicationConstants;

/**
 * @author PRABHAKAR PAL
 * @version 1.0
 * @since 2025-11-25
 * @implNote This class is used to handle common utilities in the application.
 */
public final class CommonUtils {

    private CommonUtils() {
    }

    /**
     * @implNote This method is used to get the current request id.
     * @return String
     */
    public static String getCurrentRequestId() {
        return MDC.get(ApplicationConstants.CORRELATION_ID);
    }

}

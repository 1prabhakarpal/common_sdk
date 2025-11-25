package com.common.sdk.common_sdk.utils;

import java.util.UUID;

public final class CommonUtils {

    private CommonUtils() {
    }

    public static String generateCorrelationId() {
        return UUID.randomUUID().toString();
    }

}

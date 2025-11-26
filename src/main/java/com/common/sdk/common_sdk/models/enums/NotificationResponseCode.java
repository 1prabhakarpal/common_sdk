package com.common.sdk.common_sdk.models.enums;

import com.common.sdk.common_sdk.models.interfaces.ResponseCode;

import lombok.Getter;

/**
 * @author PRABHAKAR PAL
 * @version 1.0
 * @since 2025-11-25
 * @implNote This enum is used to store response codes in the application.
 */
@Getter
public enum NotificationResponseCode implements ResponseCode {
    SUCCESS(200, "Notification sent successfully"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    TEMPLATE_NOT_FOUND(1001, "Notification Template not found");

    private final int code;
    private final String message;

    NotificationResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}

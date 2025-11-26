package com.common.sdk.common_sdk.models.interfaces;

/**
 * @author PRABHAKAR PAL
 * @version 1.0
 * @since 2025-11-25
 * @implNote This interface is used to store generic api response in the
 *           application.
 */
public interface GenericApiResponse<R> {
    /**
     * @implNote This method is used to check if the response is successful.
     * @return boolean
     */
    boolean isSuccess();

    /**
     * @implNote This method is used to get the data from the response.
     * @return R
     */
    R getData();

    /**
     * @implNote This method is used to get the code from the response.
     * @return Integer
     */
    Integer getCode();

    /**
     * @implNote This method is used to get the error message from the response.
     * @return String
     */
    String getErrorMessage();
}

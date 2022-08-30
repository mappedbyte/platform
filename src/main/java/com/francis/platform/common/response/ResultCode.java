package com.francis.platform.common.response;

/**
 * @author Francis
 */
public interface ResultCode {

    /**
     * Returns the status of a boolean
     * @return
     */
    boolean success();

    /**
     * return status code
     * @return
     */
    int code();

    /**
     * return status message
     * @return
     */
    String message();


}

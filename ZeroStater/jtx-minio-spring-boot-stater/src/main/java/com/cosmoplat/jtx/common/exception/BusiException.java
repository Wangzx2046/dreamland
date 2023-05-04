package com.cosmoplat.jtx.common.exception;


import lombok.Data;

/**
 * @author kaiye
 * 业务异常
 */

@Data
public class BusiException extends JtxServiceException {

    private Integer code;
    private String message;

    public BusiException(String message) {
        super(message);
        this.message = message;
    }

    public BusiException(Integer errorCode, String message) {
        super(errorCode, message);
        this.code = errorCode;
        this.message = message;
    }


    public BusiException(String message, Throwable cause) {
        super(message, cause);
    }
}
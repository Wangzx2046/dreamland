package com.cosmoplat.jtx.common.exception;


import com.cosmoplat.jtx.common.result.IResultType;

public class JtxServiceException extends BaseException {
    public JtxServiceException() {
        super(-1, "");
    }

    public JtxServiceException(String message) {
        super(-1, message);
    }

    public JtxServiceException(Integer code, String message) {
        super(code, message);
    }

    public JtxServiceException(IResultType resultType) {
        super(resultType);
    }

    public JtxServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}

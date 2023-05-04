//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.cosmoplat.jtx.common.exception;


import com.cosmoplat.jtx.common.result.IResultType;

public class BaseException extends RuntimeException {
    private static final long serialVersionUID = -9070366652885045261L;
    private Integer code;
    private String message;

    public BaseException(IResultType resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMessage();
        this.doFillInStackTrace();
    }

    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
        this.doFillInStackTrace();
    }

    public BaseException(String message, Throwable cause) {
        super(message);
        this.doFillInStackTrace();
    }

    public Throwable fillInStackTrace() {
        return this;
    }

    public Throwable doFillInStackTrace() {
        return super.fillInStackTrace();
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof BaseException)) {
            return false;
        } else {
            BaseException other = (BaseException)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (!super.equals(o)) {
                return false;
            } else {
                Object this$code = this.getCode();
                Object other$code = other.getCode();
                if (this$code == null) {
                    if (other$code != null) {
                        return false;
                    }
                } else if (!this$code.equals(other$code)) {
                    return false;
                }

                Object this$message = this.getMessage();
                Object other$message = other.getMessage();
                if (this$message == null) {
                    if (other$message != null) {
                        return false;
                    }
                } else if (!this$message.equals(other$message)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof BaseException;
    }

    public int hashCode() {
        int PRIME = 1;
        int result = super.hashCode();
        Object $code = this.getCode();
        result = result * 59 + ($code == null ? 43 : $code.hashCode());
        Object $message = this.getMessage();
        result = result * 59 + ($message == null ? 43 : $message.hashCode());
        return result;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toString() {
        return "BaseException(code=" + this.getCode() + ", message=" + this.getMessage() + ")";
    }
}

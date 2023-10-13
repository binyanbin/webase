package com.bin.webase.exception;

/**
 * @author :yanbin
 */
public class ApplicationException extends RuntimeException {

    private Integer code;
    private String reasoning;

    public ApplicationException() {
        super();
    }

    public ApplicationException(ErrorCode errorCode, String reasoning) {
        super();
        this.code = errorCode.getCode();
        this.reasoning = reasoning;
    }

    public ApplicationException(ErrorCode errorCode) {
        super();
        this.code = errorCode.getCode();
        this.reasoning = errorCode.getReasoning();
    }

    /**
     * @return the code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * @return the reasoning
     */
    public String getReasoning() {
        return reasoning;
    }

    /**
     * @param reasoning the reasoning to set
     */
    public void setReasoning(String reasoning) {
        this.reasoning = reasoning;
    }
}

package com.tangyx.forum.exception;

/**
 * @author tangyx
 * @date 2021/12/8 23:05
 * +
 */
public class CustomException extends RuntimeException {

    private Integer code;
    private String message;

    public CustomException(CustomizeErrorCode code_message) {
        this.code = code_message.getCode();
        this.message = code_message.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}

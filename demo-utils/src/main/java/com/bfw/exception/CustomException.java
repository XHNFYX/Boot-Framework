package com.bfw.exception;

import com.bfw.enumeration.HttpEnum;

/**
 * @BelongsProject: Boot-Framework
 * @BelongsPackage: com.bfw.config
 * @Author: 风花雪月
 * @CreateTime: 2024-08-30  21:33
 * @Description:自定义异常类
 * @Version: 1.0
 */
public class CustomException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    protected Integer errorCode;
    protected String errorMsg;

    public CustomException() {}
    public CustomException(HttpEnum httpEnum) {
        this.errorCode = httpEnum.getCode();
        this.errorMsg=httpEnum.getMessage();
    }

    public CustomException(Throwable cause, Integer errorCode, String errorMsg) {
        super(cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public CustomException(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public CustomException(String errorMsg) {
        this.errorMsg = errorMsg;
    }



    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}

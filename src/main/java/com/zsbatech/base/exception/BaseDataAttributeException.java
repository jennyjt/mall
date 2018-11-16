package com.zsbatech.base.exception;

/**
 * Created by wade on 2017/11/7 9:50.
 * Service 层异常
 */
public class BaseDataAttributeException extends BaseException{

    private static final long serialVersionUID = 7699879065666362048L;

    /************************** debug info end

     * @param
    code
     * @param
    message
     * @param
    data
     * @param cause
     **************************/
    public BaseDataAttributeException(Integer code, String message, Object data, Throwable cause) {
        super(code, message, data, cause);
    }

    public BaseDataAttributeException(Integer code, String message, Object data) {
        super(code, message, data);
    }

    public BaseDataAttributeException(Integer code, String message) {
        super(code, message);
    }

    public BaseDataAttributeException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseDataAttributeException() {
        super();
    }

    public BaseDataAttributeException(String message) {
        super(message);
    }

    @Override
    public Integer getCode() {
        return super.getCode();
    }

    @Override
    public Object getData() {
        return super.getData();
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public Throwable getCause() {
        return super.getCause();
    }

    @Override
    public String getClassName() {
        return super.getClassName();
    }

    @Override
    public String getFileName() {
        return super.getFileName();
    }

    @Override
    public String getMethodName() {
        return super.getMethodName();
    }

    @Override
    public Integer getLineNumber() {
        return super.getLineNumber();
    }
}

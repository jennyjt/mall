package com.zsbatech.base.exception;

/**
 * Created by wade on 2017/11/7 9:50.
 * Service 层异常
 */
public class BaseDataCategropyException extends BaseException{

    private static final long serialVersionUID = 7699879065066362048L;

    /************************** debug info end

     * @param
    code
     * @param
    message
     * @param
    data
     * @param cause
     **************************/
    public BaseDataCategropyException(Integer code, String message, Object data, Throwable cause) {
        super(code, message, data, cause);
    }

    public BaseDataCategropyException(Integer code, String message, Object data) {
        super(code, message, data);
    }

    public BaseDataCategropyException(Integer code, String message) {
        super(code, message);
    }

    public BaseDataCategropyException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseDataCategropyException() {
        super();
    }

    public BaseDataCategropyException(String message) {
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

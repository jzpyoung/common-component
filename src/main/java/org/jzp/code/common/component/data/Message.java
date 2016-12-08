package org.jzp.code.common.component.data;

import java.io.Serializable;

/**
 * 返回结果
 *
 * @author jiazhipeng
 * @version 1.0
 * @date 2016-11-02
 */
public class Message<D> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 结果。
     */
    private Result result;

    /**
     * 编码(可表示错误码等)。
     */
    private int code;

    /**
     * 错误信息编码(可表示错误码等)。
     */
    private String message;

    /**
     * 数据。
     */
    private D data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Message() {
    }

    public Message(Result result, int code) {
        super();
        this.result = result;
        this.code = code;
    }

    public Message(Result result, Integer code, String message) {
        super();
        this.result = result;
        this.code = code;
        this.message = message;
    }

    public Message(Result result, int code, String message, D data) {
        super();
        this.result = result;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Message(Result result, D data) {
        super();
        this.result = result;
        this.data = data;
    }

    public Message(Result result, int code, D data) {
        super();
        this.result = result;
        this.code = code;
        this.data = data;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }
}

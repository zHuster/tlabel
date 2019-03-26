package com.csdc.exception;

/**
 * @author zhangzhi
 * @since <pre>2019/3/20</pre>
 */
public class OperateException extends RuntimeException {
    public OperateException() {
        super();
    }

    public OperateException(OperateError error) {
        super(error.toString());
    }
}

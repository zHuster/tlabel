package com.csdc.exception;

/**
 * @author zhangzhi
 * @since <pre>2019.3.8</pre>
 * <p>
 * 自定义请求异常类
 */

public class RequestException extends RuntimeException {

    public RequestException() {
        super();
    }

    public RequestException(RequestError requestError) {
        super(requestError.toString());
    }
}

package com.csdc.exception;

/**
 * @author zhangzhi
 * @since <pre>2019.3.8</pre>
 * <p>
 * 自定义请求异常类
 */

public class RequestException extends RuntimeException {

    private RequestError requestError;

    public RequestException(RequestError requestError) {
        super(requestError.toString());
        this.requestError=requestError;
    }

    public RequestError getRequestError() {
        return requestError;
    }
}

package com.csdc.exception.handler;

import com.csdc.entity.json.ErrorInfo;
import com.csdc.exception.RequestException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author zhangzhi
 * @since <pre>2019/3/28</pre>
 * <p>
 * 配置全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    protected ResponseEntity<Object> handleRequestException(RequestException reqException, WebRequest request) {
        ErrorInfo errorInfo = new ErrorInfo(HttpStatus.BAD_REQUEST.value(), reqException.getRequestError().toString());
        return handleExceptionInternal(reqException, errorInfo, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

}

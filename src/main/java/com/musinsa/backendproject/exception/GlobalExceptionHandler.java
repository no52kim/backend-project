package com.musinsa.backendproject.exception;

import com.musinsa.backendproject.common.CommonResponse;
import com.musinsa.backendproject.common.RequestErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import reactor.core.publisher.Mono;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Mono<CommonResponse<String>> handleException(Exception e) {
        return Mono.just(CommonResponse.error(RequestErrorCode.INTERNAL_SERVER_ERROR, "서버에서 에러가 발생하였습니다."));
    }
}
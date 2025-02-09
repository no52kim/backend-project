package com.musinsa.backendproject.exception;

import com.musinsa.backendproject.common.CommonResponse;
import com.musinsa.backendproject.common.RequestErrorCode;
import reactor.core.publisher.Mono;

public class RequestExceptionHandler {
    public static <T> Mono<CommonResponse<T>> handleError(Throwable e) {
        if (e instanceof DatabaseException) {
            return Mono.just(CommonResponse.error(RequestErrorCode.DATABASE_ERROR, "데이터베이스 에러가 발생했습니다."));
        } else if (e instanceof Exception) {
            return Mono.just(CommonResponse.error(RequestErrorCode.UNEXPECTED_ERROR, "서버에서 에러가 발생하였습니다."));
        } else {
            return Mono.just(CommonResponse.error(RequestErrorCode.INTERNAL_SERVER_ERROR, "Internal Server Error"));
        }
    }
}

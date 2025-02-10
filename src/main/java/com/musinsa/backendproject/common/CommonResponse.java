package com.musinsa.backendproject.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> {
    private int code;
    private String message;
    private T data;

    public static <T> CommonResponse<T> success(T data) {
        return CommonResponse.<T>builder()
                .code(RequestErrorCode.SUCCESS)
                .message("Success")
                .data(data)
                .build();
    }

    public static <T> CommonResponse<T> error(int code, String message) {
        return CommonResponse.<T>builder()
                .code(code)
                .message(message)
                .build();
    }
}
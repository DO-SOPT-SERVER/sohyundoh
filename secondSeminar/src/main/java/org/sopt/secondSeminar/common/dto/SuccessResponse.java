package org.sopt.secondSeminar.common.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.sopt.secondSeminar.exception.Success;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SuccessResponse<T> {
    private final int status;
    private final String message;
    private T data;


    public static <T> SuccessResponse<T> success(Success success, T data) {
        return new SuccessResponse<>(success.getStatus().value(), success.getMessage(), data);
    }

    public static SuccessResponse success(Success success) {
        return new SuccessResponse(success.getStatus().value(), success.getMessage());
    }
}

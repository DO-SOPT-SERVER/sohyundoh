package org.sopt.sixthSeminar.common.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.sopt.thirdSeminar.exception.SuccessMessage;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SuccessResponse<T> {
    private final int status;
    private final String message;
    private T data;


    public static <T> SuccessResponse<T> success(final SuccessMessage success, final T data) {
        return new SuccessResponse<>(success.getStatus().value(), success.getMessage(), data);
    }

    public static SuccessResponse success(final SuccessMessage success) {
        return new SuccessResponse(success.getStatus().value(), success.getMessage());
    }
}

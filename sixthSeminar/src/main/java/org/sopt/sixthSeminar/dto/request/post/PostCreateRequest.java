package org.sopt.sixthSeminar.dto.request.post;

public record PostCreateRequest(
        String title,
        String content,
        String categoryId
) {
}
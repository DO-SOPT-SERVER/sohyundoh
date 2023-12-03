package org.sopt.sixthSeminar.dto.response.category;

import org.sopt.sixthSeminar.domain.Category;

public record CategoryResponse(
        String category
) {
    public static CategoryResponse of(Category category) {
        return new CategoryResponse(category.getContent());
    }
}

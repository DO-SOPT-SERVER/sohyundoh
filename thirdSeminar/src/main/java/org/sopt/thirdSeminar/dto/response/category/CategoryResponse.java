package org.sopt.thirdSeminar.dto.response.category;

import org.sopt.thirdSeminar.domain.Category;

public record CategoryResponse(
        String category
) {
    public static CategoryResponse of(Category category) {
        return new CategoryResponse(category.getContent());
    }
}

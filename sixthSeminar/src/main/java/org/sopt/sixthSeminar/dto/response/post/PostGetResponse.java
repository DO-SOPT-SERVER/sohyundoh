package org.sopt.sixthSeminar.dto.response.post;

import org.sopt.thirdSeminar.domain.Category;
import org.sopt.thirdSeminar.domain.Post;
import org.sopt.thirdSeminar.service.CategoryService;

public record PostGetResponse(
        Long id,
        String title,
        String content,
        String category
) {
    public static PostGetResponse of(Post post, Category category) {
        return new PostGetResponse(post.getId(), post.getTitle(), post.getContent(), category.getContent());
    }
}

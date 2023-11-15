package org.sopt.secondKotlin.dto.response

import org.sopt.secondKotlin.domain.Category
import org.sopt.secondKotlin.domain.Post

data class PostGetResponse(
    val id: Long,
    val title: String,
    val content: String,
    val category: String,
) {
    companion object {
        fun of(post: Post, category: Category): PostGetResponse {
            return PostGetResponse(
                post.id,
                post.title,
                post.content,
                category.content)
        }
    }
}
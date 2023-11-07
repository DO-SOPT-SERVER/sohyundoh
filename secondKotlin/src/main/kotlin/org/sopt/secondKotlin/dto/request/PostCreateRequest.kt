package org.sopt.secondKotlin.dto.request


data class PostCreateRequest(
    val title: String,
    val content: String,
    val categoryId: Short
)
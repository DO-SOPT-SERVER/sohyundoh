package org.sopt.sixthKotlin.repository

import org.sopt.sixthKotlin.domain.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostJpaRepository : JpaRepository<Post, Long> {
}
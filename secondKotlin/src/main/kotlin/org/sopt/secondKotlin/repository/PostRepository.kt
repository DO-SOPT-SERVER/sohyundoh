package org.sopt.secondKotlin.repository

import org.sopt.secondKotlin.domain.Member
import org.sopt.secondKotlin.domain.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Long> {
    fun findAllByMember(member: Member) : List<Post>
}
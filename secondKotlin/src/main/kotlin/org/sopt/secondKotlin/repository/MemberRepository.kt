package org.sopt.secondKotlin.repository

import org.sopt.secondKotlin.domain.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {
}
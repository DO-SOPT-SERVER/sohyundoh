package org.sopt.sixthKotlin.repository

import org.sopt.sixthKotlin.domain.ServiceMember
import org.springframework.data.jpa.repository.JpaRepository

interface ServiceMemberJpaRepository : JpaRepository<ServiceMember, Long> {
    fun findByNickname(nickname : String) : ServiceMember?

}
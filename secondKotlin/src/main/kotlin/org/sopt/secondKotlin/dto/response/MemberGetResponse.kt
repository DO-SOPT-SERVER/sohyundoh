package org.sopt.secondKotlin.dto.response

import org.sopt.secondKotlin.domain.Member
import org.sopt.secondKotlin.domain.SOPT

class MemberGetResponse(
    val name: String,
    val nickname: String,
    val age: Int,
    val sopt: SOPT
) {
    companion object {
        fun of(
            member: Member
        ): MemberGetResponse {
            return MemberGetResponse(
                member.name,
                member.nickname,
                member.age,
                member.SOPT
            )
        }
    }
}
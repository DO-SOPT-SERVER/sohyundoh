package org.sopt.secondKotlin.dto.request

import org.sopt.secondKotlin.domain.SOPT

class MemberCreateRequest(
    val name :String,
    val nickname: String,
    val age : Int,
    val sopt: SOPT
) {
}
package org.sopt.secondKotlin.dto.request

import org.sopt.secondKotlin.domain.enums.Part

class MemberProfileUpdateRequest(
    val generation : String,
    val part : Part
) {
}
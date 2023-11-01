package org.sopt.secondKotlin.domain

import jakarta.persistence.Embeddable
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import org.sopt.secondKotlin.domain.enums.Part

@Embeddable
class SOPT(
    private val generation : Int,

    @Enumerated(value = EnumType.STRING)
    private val part : Part
) {
}
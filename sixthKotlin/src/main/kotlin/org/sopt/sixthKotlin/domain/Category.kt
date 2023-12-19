package org.sopt.sixthKotlin.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
class Category(
    val content: String? = null,

    @Id
    @GeneratedValue
    val id: Short = 0,
) {
}
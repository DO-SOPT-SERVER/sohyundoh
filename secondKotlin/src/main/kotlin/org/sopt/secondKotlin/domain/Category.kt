package org.sopt.secondKotlin.domain

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
class Category(
    var content: String,
    @Id
    val id: CategoryId
) {
}
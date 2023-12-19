package org.sopt.sixthKotlin.domain

import jakarta.persistence.Embeddable
import lombok.Builder
import lombok.EqualsAndHashCode
import java.io.Serializable

@Embeddable
@EqualsAndHashCode
class CategoryId @Builder private constructor(val categoryId: String) : Serializable
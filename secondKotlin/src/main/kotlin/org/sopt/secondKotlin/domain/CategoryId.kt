package org.sopt.secondKotlin.domain

import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
class CategoryId(val id : Short) : Serializable{
}
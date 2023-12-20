package org.sopt.sixthKotlin.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id


@Entity
class ServiceMember(
    val nickname: String,
    val password: String,
    @Id
    @GeneratedValue
    val id: Long? = null
) {

}
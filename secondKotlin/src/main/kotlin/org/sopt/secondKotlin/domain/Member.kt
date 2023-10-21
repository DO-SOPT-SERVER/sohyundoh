package org.sopt.secondKotlin.domain

import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.sopt.secondKotlin.dto.request.MemberCreateRequest

@Entity
class Member(
    var name: String,
    var nickname: String,
    var age: Int,

    @Embedded
    var SOPT: SOPT,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
) {
    companion object{
        fun create(request: MemberCreateRequest): Member {
            return Member(request.name, request.nickname, request.age, request.sopt)
        }
    }
}
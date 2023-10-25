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
    val id: Long? = 0 //추후 테스트코드 작성을 위해 우선 기본생성자로 부여할 수 있게 하겠습니다
) {
    companion object{
        fun create(request: MemberCreateRequest): Member {
            return Member(request.name, request.nickname, request.age, request.sopt)
        }
    }
}
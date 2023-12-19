package org.sopt.sixthKotlin.service

import lombok.extern.slf4j.Slf4j
import org.sopt.sixthKotlin.config.JwtTokenProvider
import org.sopt.sixthKotlin.config.authentication.UserAuthentication
import org.sopt.sixthKotlin.controller.dto.request.ServiceMemberRequest
import org.sopt.sixthKotlin.controller.dto.response.MemberSignInResponse
import org.sopt.sixthKotlin.domain.ServiceMember
import org.sopt.sixthKotlin.exception.ErrorMessage
import org.sopt.sixthKotlin.exception.SoptException.AuthorizationException
import org.sopt.sixthKotlin.exception.SoptException.MemberNotFoundException
import org.sopt.sixthKotlin.repository.ServiceMemberJpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ServiceMemberService(
    private val serviceMemberJpaRepository: ServiceMemberJpaRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtTokenProvider: JwtTokenProvider
) {
    @Transactional
    fun create(request: ServiceMemberRequest): String {
        val serviceMember = ServiceMember(request.nickname, passwordEncoder.encode(request.password))
        serviceMemberJpaRepository.save(serviceMember)
        return serviceMember.id.toString()
    }

    fun findById(memberId: Long): ServiceMember {
        return serviceMemberJpaRepository.findByIdOrNull(memberId) ?: throw MemberNotFoundException(ErrorMessage.MEMBER_NOT_FOUND)
    }

    fun signIn(request: ServiceMemberRequest): MemberSignInResponse {
        val serviceMember: ServiceMember =
            serviceMemberJpaRepository.findByNickname(request.nickname) ?: throw MemberNotFoundException(ErrorMessage.MEMBER_NOT_FOUND)
        val userAuthentication = UserAuthentication(serviceMember.id, null, null)
        if (!passwordEncoder.matches(request.password, serviceMember.password)) {
            throw AuthorizationException(ErrorMessage.PASSWORD_INCORRECT)
        }
        return MemberSignInResponse(jwtTokenProvider.generateToken(userAuthentication))
    }
}
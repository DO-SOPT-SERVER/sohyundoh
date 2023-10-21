package org.sopt.secondKotlin.service;

import jakarta.persistence.EntityNotFoundException
import org.sopt.secondKotlin.domain.Member
import org.sopt.secondKotlin.dto.request.MemberCreateRequest
import org.sopt.secondKotlin.dto.request.MemberProfileUpdateRequest
import org.sopt.secondKotlin.dto.response.MemberGetResponse
import org.sopt.secondKotlin.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberService(
    private val memberRepository: MemberRepository
) {

    @Transactional
    fun create(request: MemberCreateRequest): String {
        val member: Member = memberRepository.save(Member.create(request))
        return member.id.toString()
    }

    @Transactional
    fun updateSopt(
        memberId: Long,
        updateRequest: MemberProfileUpdateRequest
    ) {
        val member: Member = memberRepository.findById(memberId).orElseThrow {
            throw EntityNotFoundException("존재하지 않는 회원입니다.")
        } //Nullable로 사용하는 방법 생각해보기

    }

    fun getById(
        memberId: Long
    ): MemberGetResponse {
        val member: Member = memberRepository.findById(memberId).orElseThrow {
            throw EntityNotFoundException("존재하지 않는 회원입니다.")
        }
        return MemberGetResponse.of(member)
    }

    fun getMembers(): List<MemberGetResponse> {
        return memberRepository.findAll()
            .map { MemberGetResponse.of(it) }
            .toList() //kotlin의 it
    }

    @Transactional
    fun delete(
        memberId: Long
    ) {
        val member: Member = memberRepository.findById(memberId).orElseThrow {
            EntityNotFoundException("존재하지 않는 회원입니다.")
        }
        memberRepository.delete(member)
    }
}

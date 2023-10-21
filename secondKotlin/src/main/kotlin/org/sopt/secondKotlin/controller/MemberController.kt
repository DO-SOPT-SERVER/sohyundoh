package org.sopt.secondKotlin.controller;

import org.sopt.secondKotlin.dto.request.MemberCreateRequest
import org.sopt.secondKotlin.dto.request.MemberProfileUpdateRequest
import org.sopt.secondKotlin.dto.response.MemberGetResponse
import org.sopt.secondKotlin.service.MemberService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/api/member")
class MemberController(
    private val memberService: MemberService
) {
    @PostMapping
    fun createMember(
        @RequestBody request: MemberCreateRequest
    ): ResponseEntity<Void> {
        val location: URI = URI.create("/api/member" + memberService.create(request))
        return ResponseEntity.created(location).build()
    }

    @GetMapping
    fun getMembersProfile()
            : ResponseEntity<List<MemberGetResponse>> {
        return ResponseEntity.ok(memberService.getMembers())
    }

    @GetMapping("/{memberId}")
    fun getMemberProfile(
        @PathVariable memberId: Long
    ): ResponseEntity<MemberGetResponse> {
        return ResponseEntity.ok(memberService.getById(memberId))
    }

    @PatchMapping("/{memberId}")
    fun updateMemberSoptInfo(
        @PathVariable memberId: Long,
        @RequestBody request: MemberProfileUpdateRequest
    ): ResponseEntity<Void> {
        memberService.updateSopt(memberId, request)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/{memberId}")
    fun delete(
        @PathVariable memberId: Long
    ): ResponseEntity<Void> {
        memberService.delete(memberId)
        return ResponseEntity.noContent().build()
    }
}

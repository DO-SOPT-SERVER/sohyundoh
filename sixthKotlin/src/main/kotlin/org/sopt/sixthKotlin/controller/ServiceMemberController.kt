package org.sopt.sixthKotlin.controller

import org.sopt.sixthKotlin.controller.dto.request.ServiceMemberRequest
import org.sopt.sixthKotlin.controller.dto.response.MemberSignInResponse
import org.sopt.sixthKotlin.service.ServiceMemberService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users/")
class ServiceMemberController(
    private val serviceMemberService: ServiceMemberService
) {
    @PostMapping("sign-up")
    fun signUp(@RequestBody request: ServiceMemberRequest): ResponseEntity<Void> {
        serviceMemberService.create(request)
        return ResponseEntity.ok().build()
    }

    @PostMapping("sign-in")
    fun signIn(@RequestBody request: ServiceMemberRequest): ResponseEntity<MemberSignInResponse> {
        return ResponseEntity.ok(serviceMemberService.signIn(request))
    }
}
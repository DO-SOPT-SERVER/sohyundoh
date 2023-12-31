package org.sopt.sixthSeminar.controller;

import lombok.RequiredArgsConstructor;
import org.sopt.sixthSeminar.common.dto.SuccessResponse;
import org.sopt.sixthSeminar.dto.request.serviceMember.ServiceMemberRequest;
import org.sopt.sixthSeminar.dto.response.member.MemberSignInResponse;
import org.sopt.sixthSeminar.exception.SuccessMessage;
import org.sopt.sixthSeminar.service.ServiceMemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/")
public class ServiceMemberController {

    private final ServiceMemberService serviceMemberService;

    @PostMapping("sign-up")
    public SuccessResponse signUp(final @RequestBody ServiceMemberRequest request) {
        return SuccessResponse.success(SuccessMessage.MEMBER_CREATE_SUCCESS);
    }

    @PostMapping("sign-in")
    public SuccessResponse<MemberSignInResponse> signIn(final @RequestBody ServiceMemberRequest request) {
        return SuccessResponse.success(SuccessMessage.MEMBER_SIGN_IN_SUCCESS,
                serviceMemberService.signIn(request));
    }
}

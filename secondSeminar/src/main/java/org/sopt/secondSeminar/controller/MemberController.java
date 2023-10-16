package org.sopt.secondSeminar.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.sopt.secondSeminar.common.dto.SuccessResponse;
import org.sopt.secondSeminar.dto.request.MemberCreateRequest;
import org.sopt.secondSeminar.dto.request.MemberProfileUpdateRequest;
import org.sopt.secondSeminar.dto.response.MemberGetResponse;
import org.sopt.secondSeminar.exception.Success;
import org.sopt.secondSeminar.service.MemberService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@RequestMapping("api/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping(value = "{memberId}/V2", produces = MediaType.APPLICATION_JSON_VALUE)
    public SuccessResponse<MemberGetResponse> getMemberProfileV2(
            @PathVariable("memberId") Long memberId) {
        return SuccessResponse.success(Success.MEMBER_SEARCH_SUCCESS, memberService.getById(memberId));
    }

    @PostMapping
    public SuccessResponse createMember(@RequestBody MemberCreateRequest request, HttpServletResponse response) {
        String location = "api/member/" + memberService.create(request);
        response.setHeader("Location", location);
        return SuccessResponse.success(Success.MEMBER_CREATE_SUCCESS);
    }

    @GetMapping
    public SuccessResponse<List<MemberGetResponse>> getMembersProfile() {
        return SuccessResponse.success(Success.ALL_MEMBERS_SEARCH_SUCCESS, memberService.getMembers());
    }

    @PatchMapping("/{memberId}")
    public SuccessResponse updateMemberSoptInfo(@PathVariable Long memberId,
                                                @RequestBody MemberProfileUpdateRequest request) {
        memberService.updateSOPT(memberId, request);
        return SuccessResponse.success(Success.MEMBER_UPDATE_SUCCESS);
    }

    @DeleteMapping("/{memberId}")
    public SuccessResponse deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
        return SuccessResponse.success(Success.MEMBER_DELETE_SUCCESS);
    }
}

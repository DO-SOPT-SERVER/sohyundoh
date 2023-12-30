package org.sopt.sixthSeminar.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.sopt.sixthSeminar.common.dto.SuccessResponse;
import org.sopt.sixthSeminar.dto.request.member.MemberCreateRequest;
import org.sopt.sixthSeminar.dto.request.member.MemberProfileUpdateRequest;
import org.sopt.sixthSeminar.dto.response.member.MemberGetResponse;
import org.sopt.sixthSeminar.exception.SuccessMessage;
import org.sopt.sixthSeminar.service.MemberService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@RequestMapping("api/member")
public class MemberController {

    private final MemberService memberService;
    private static final String LOCATION_PREFIX = "api/member/";

    @GetMapping(value = "{memberId}/V2", produces = MediaType.APPLICATION_JSON_VALUE)
    public SuccessResponse<MemberGetResponse> getMemberProfileV2(
            @PathVariable("memberId") final Long memberId) {
        return SuccessResponse.success(SuccessMessage.MEMBER_SEARCH_SUCCESS, memberService.getById(memberId));
    }

    @PostMapping
    public SuccessResponse createMember(@RequestBody final MemberCreateRequest request,
                                        final HttpServletResponse response) {
        String location = LOCATION_PREFIX + memberService.create(request);
        response.setHeader("Location", location);
        return SuccessResponse.success(SuccessMessage.MEMBER_CREATE_SUCCESS);
    }

    @GetMapping
    public SuccessResponse<List<MemberGetResponse>> getMembersProfile() {
        return SuccessResponse.success(SuccessMessage.ALL_MEMBERS_SEARCH_SUCCESS, memberService.getMembers());
    }

    @PatchMapping("/{memberId}")
    public SuccessResponse updateMemberSoptInfo(@PathVariable final Long memberId,
                                                @RequestBody final MemberProfileUpdateRequest request) {
        memberService.updateSOPT(memberId, request);
        return SuccessResponse.success(SuccessMessage.MEMBER_UPDATE_SUCCESS);
    }

    @DeleteMapping("/{memberId}")
    public SuccessResponse deleteMember(@PathVariable Long memberId) {
        memberService.delete(memberId);
        return SuccessResponse.success(SuccessMessage.MEMBER_DELETE_SUCCESS);
    }
}

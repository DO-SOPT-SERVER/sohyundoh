package org.sopt.sixthSeminar.service;

import lombok.RequiredArgsConstructor;
import org.sopt.sixthSeminar.repository.MemberRepository;
import org.sopt.sixthSeminar.domain.Member;
import org.sopt.sixthSeminar.domain.SOPT;
import org.sopt.sixthSeminar.dto.request.member.MemberCreateRequest;
import org.sopt.sixthSeminar.dto.request.member.MemberProfileUpdateRequest;
import org.sopt.sixthSeminar.dto.response.member.MemberGetResponse;
import org.sopt.sixthSeminar.exception.ErrorMessage;
import org.sopt.sixthSeminar.exception.model.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;


    public MemberGetResponse getById(final Long memberId) {
        return MemberGetResponse.of(memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.MEMBER_NOT_FOUND_EXCEPTION)));
    }

    public Member findById(final Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.MEMBER_NOT_FOUND_EXCEPTION));
    }

    public List<MemberGetResponse> getMembers() {
        return memberRepository.findAll()
                .stream()
                .map(MemberGetResponse::of)
                .collect(Collectors.toList());
    }

    @Transactional
    public String create(final MemberCreateRequest request) {
        Member member = memberRepository.save(Member.create(request));
        return member.getId().toString();
    }

    @Transactional
    public void updateSOPT(final Long memberId,
                           final MemberProfileUpdateRequest updateRequest) {
        Member member = memberRepository.findByIdOrThrow(memberId);
        member.updateSOPT(new SOPT(updateRequest.generation(), updateRequest.part()));
    }

    @Transactional
    public void delete(final Long memberId) {
        Member member = memberRepository.findByIdOrThrow(memberId);
        memberRepository.delete(member);
    }
}

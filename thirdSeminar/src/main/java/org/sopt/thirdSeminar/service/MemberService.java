package org.sopt.thirdSeminar.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.sopt.thirdSeminar.Repository.MemberRepository;
import org.sopt.thirdSeminar.domain.Member;
import org.sopt.thirdSeminar.domain.SOPT;
import org.sopt.thirdSeminar.dto.request.member.MemberCreateRequest;
import org.sopt.thirdSeminar.dto.request.member.MemberProfileUpdateRequest;
import org.sopt.thirdSeminar.dto.response.member.MemberGetResponse;
import org.sopt.thirdSeminar.exception.ErrorMessage;
import org.sopt.thirdSeminar.exception.model.NotFoundException;
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

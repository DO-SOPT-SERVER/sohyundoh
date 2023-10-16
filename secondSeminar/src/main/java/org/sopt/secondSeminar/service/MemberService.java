package org.sopt.secondSeminar.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.sopt.secondSeminar.Repository.MemberRepository;
import org.sopt.secondSeminar.domain.Member;
import org.sopt.secondSeminar.domain.SOPT;
import org.sopt.secondSeminar.dto.request.MemberCreateRequest;
import org.sopt.secondSeminar.dto.request.MemberProfileUpdateRequest;
import org.sopt.secondSeminar.dto.response.MemberGetResponse;
import org.sopt.secondSeminar.exception.Error;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;


    public MemberGetResponse getById(Long memberId) {
        return MemberGetResponse.of(memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException(Error.MEMBER_NOT_FOUND_EXCEPTION.getMessage())));
    }


    public List<MemberGetResponse> getMembers() {
        return memberRepository.findAll()
                .stream()
                .map(MemberGetResponse::of)
                .collect(Collectors.toList());
    }

    @Transactional
    public String create(MemberCreateRequest request) {
        Member member = memberRepository.save(Member.create(request));
        return member.getId().toString();
    }

    @Transactional
    public void updateSOPT(Long memberId, MemberProfileUpdateRequest updateRequest) {
        Member member = memberRepository.findByIdOrThrow(memberId);
        member.updateSOPT(new SOPT(updateRequest.getGeneration(), updateRequest.getPart()));
    }

    @Transactional
    public void deleteMember(Long memberId) {
        Member member = memberRepository.findByIdOrThrow(memberId);
        memberRepository.delete(member);
    }
}

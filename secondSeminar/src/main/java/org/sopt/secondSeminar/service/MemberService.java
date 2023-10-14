package org.sopt.secondSeminar.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.sopt.secondSeminar.Repository.MemberRepository;
import org.sopt.secondSeminar.domain.Member;
import org.sopt.secondSeminar.domain.SOPT;
import org.sopt.secondSeminar.dto.request.MemberCreateRequest;
import org.sopt.secondSeminar.dto.request.MemberProfileUpdateRequest;
import org.sopt.secondSeminar.dto.response.MemberGetResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberGetResponse getByIdV1(Long memberId) {
        Member member = memberRepository.findById(memberId).get();
        return MemberGetResponse.of(member);
    }

    public MemberGetResponse getByIdV2(Long memberId) {
        return MemberGetResponse.of(memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 회원입니다.")));
    }

    public MemberGetResponse getByIdV3(Long memberId) {
        return MemberGetResponse.of(memberRepository.findByIdOrThrow(memberId));
    }

    public List<MemberGetResponse> getMembers() {
        return memberRepository.findAll()
                .stream()
                .map(MemberGetResponse::of)
                .collect(Collectors.toList());
    }

    @Transactional
    public String create(MemberCreateRequest request) {
        Member member = memberRepository.save(Member.builder()
                .name(request.getName())
                .nickname(request.getNickname())
                .age(request.getAge())
                .sopt(request.getSopt())
                .build());
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

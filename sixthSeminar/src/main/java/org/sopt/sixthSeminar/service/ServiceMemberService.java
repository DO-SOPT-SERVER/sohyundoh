package org.sopt.sixthSeminar.service;

import lombok.RequiredArgsConstructor;
import org.sopt.sixthSeminar.config.JwtTokenProvider;
import org.sopt.sixthSeminar.domain.ServiceMember;
import org.sopt.sixthSeminar.dto.request.serviceMember.ServiceMemberRequest;
import org.sopt.sixthSeminar.dto.response.member.MemberSignInResponse;
import org.sopt.sixthSeminar.exception.ErrorMessage;
import org.sopt.sixthSeminar.exception.model.AuthException;
import org.sopt.sixthSeminar.exception.model.NotFoundException;
import org.sopt.sixthSeminar.repository.ServiceMemberRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ServiceMemberService {

    private final ServiceMemberRepository serviceMemberJpaRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public String create(ServiceMemberRequest request) {
        ServiceMember serviceMember = ServiceMember.builder()
                .nickanme(request.nickname())
                .password(passwordEncoder.encode(request.password()))
                .build();
        serviceMemberJpaRepository.save(serviceMember);
        return serviceMember.getId().toString();
    }

    public MemberSignInResponse signIn(ServiceMemberRequest request) {
        ServiceMember serviceMember = serviceMemberJpaRepository.findByNickname(request.nickname())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.MEMBER_NOT_FOUND_EXCEPTION));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!passwordEncoder.matches(request.password(), serviceMember.getPassword())) {
            throw new AuthException(ErrorMessage.PASSWORD_INCORRECT_EXCEPTION);
        }
        return MemberSignInResponse.of(jwtTokenProvider.generateToken(authentication));
    }

}
package org.sopt.sixthSeminar.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sopt.sixthSeminar.config.JwtTokenProvider;
import org.sopt.sixthSeminar.config.authentication.UserAuthentication;
import org.sopt.sixthSeminar.domain.ServiceMember;
import org.sopt.sixthSeminar.dto.request.serviceMember.ServiceMemberRequest;
import org.sopt.sixthSeminar.dto.response.member.MemberSignInResponse;
import org.sopt.sixthSeminar.exception.ErrorMessage;
import org.sopt.sixthSeminar.exception.model.AuthException;
import org.sopt.sixthSeminar.exception.model.NotFoundException;
import org.sopt.sixthSeminar.repository.ServiceMemberRepository;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ServiceMemberService {

    private final ServiceMemberRepository serviceMemberJpaRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Transactional
    public String create(final ServiceMemberRequest request) {
        ServiceMember serviceMember = ServiceMember.builder()
                .nickanme(request.nickname())
                .password(passwordEncoder.encode(request.password()))
                .build();
        serviceMemberJpaRepository.save(serviceMember);
        return serviceMember.getId().toString();
    }

       public ServiceMember findById(final Long memberId) {
        return serviceMemberJpaRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.MEMBER_NOT_FOUND_EXCEPTION));
    }

    public MemberSignInResponse signIn(final ServiceMemberRequest request) {
        ServiceMember serviceMember = serviceMemberJpaRepository.findByNickname(request.nickname())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.MEMBER_NOT_FOUND_EXCEPTION));

        UserAuthentication userAuthentication = new UserAuthentication(serviceMember.getId(), null, null);

        if (!passwordEncoder.matches(request.password(), serviceMember.getPassword())) {
            throw new AuthException(ErrorMessage.PASSWORD_INCORRECT_EXCEPTION);
        }

        return MemberSignInResponse.of(jwtTokenProvider.generateToken(userAuthentication));
    }

}
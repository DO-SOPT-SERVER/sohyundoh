package org.sopt.sixthKotlin.config.authentication

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority

class UserAuthentication  // 사용자 인증 객체 생성
    (principal: Any?, credentials: Any?, authorities: Collection<GrantedAuthority?>?) :
    UsernamePasswordAuthenticationToken(principal, credentials, authorities) 
package org.sopt.sixthKotlin.config.filter

import io.micrometer.common.lang.NonNull
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.sopt.sixthKotlin.config.JwtTokenProvider
import org.sopt.sixthKotlin.config.JwtValidationType
import org.sopt.sixthKotlin.config.authentication.UserAuthentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException

@Component
class JwtAuthenticationFilter(
    private val jwtTokenProvider: JwtTokenProvider?
) : OncePerRequestFilter(
) {
    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        @NonNull request: HttpServletRequest,
        @NonNull response: HttpServletResponse,
        @NonNull filterChain: FilterChain
    ) {
        try {
            val token : String? = getJwtFromRequest(request)
            if (jwtTokenProvider!!.validateToken(token) == JwtValidationType.VALID_JWT) {
                val memberId = jwtTokenProvider.getUserFromJwt(token)
                // authentication 객체 생성 -> principal에 유저정보를 담는다.
                val authentication = UserAuthentication(memberId.toString(), null, null)
                authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authentication
            }
        } catch (exception: Exception) {
            try {
                throw Exception()
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }
        // 다음 필터로 요청 전달
        filterChain.doFilter(request, response)
    }

    private fun getJwtFromRequest(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader("Authorization")
        return if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            bearerToken.substring("Bearer ".length)
        } else null
    }
}
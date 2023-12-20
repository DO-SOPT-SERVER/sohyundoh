package org.sopt.sixthKotlin.config

import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Header
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.UnsupportedJwtException
import io.jsonwebtoken.security.Keys
import jakarta.annotation.PostConstruct
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtTokenProvider {
    @Value("\${jwt.secret}")
    private var JWT_SECRET: String? = null
    @PostConstruct
    protected fun init() {
        //base64 라이브러리에서 encodeToString을 이용해서 byte[] 형식을 String 형식으로 변환
        JWT_SECRET = Base64.getEncoder().encodeToString(JWT_SECRET!!.toByteArray(StandardCharsets.UTF_8))
    }

    fun generateToken(authentication: Authentication): String {
        val now = Date()
        val claims = Jwts.claims()
            .setIssuedAt(now)
            .setExpiration(Date(now.time + TOKEN_EXPIRATION_TIME)) // 만료 시간 설정
        claims[MEMBER_ID] = authentication.principal
        return Jwts.builder()
            .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // Header
            .setClaims(claims) // Claim
            .signWith(signingKey) // Signature
            .compact()
    }

    private val signingKey: SecretKey
        private get() {
            val encodedKey = Base64.getEncoder().encodeToString(JWT_SECRET!!.toByteArray()) //SecretKey 통해 서명 생성
            return Keys.hmacShaKeyFor(encodedKey.toByteArray()) //일반적으로 HMAC (Hash-based Message Authentication Code) 알고리즘 사용
        }

    fun validateToken(token: String?): JwtValidationType {
        return try {
            val claims = getBody(token)
            JwtValidationType.VALID_JWT
        } catch (ex: MalformedJwtException) {
            JwtValidationType.INVALID_JWT_TOKEN
        } catch (ex: ExpiredJwtException) {
            JwtValidationType.EXPIRED_JWT_TOKEN
        } catch (ex: UnsupportedJwtException) {
            JwtValidationType.UNSUPPORTED_JWT_TOKEN
        } catch (ex: IllegalArgumentException) {
            JwtValidationType.EMPTY_JWT
        }
    }

    private fun getBody(token: String?): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(signingKey)
            .build()
            .parseClaimsJws(token)
            .body
    }

    fun getUserFromJwt(token: String?): Long {
        val claims = getBody(token)
        return claims[MEMBER_ID].toString().toLong()
    }

    companion object {
        private const val MEMBER_ID = "memberId"
        private const val TOKEN_EXPIRATION_TIME = 24 * 60 * 60 * 1000L
    }
}
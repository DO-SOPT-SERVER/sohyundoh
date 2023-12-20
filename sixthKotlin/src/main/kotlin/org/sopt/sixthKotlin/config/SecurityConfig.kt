package org.sopt.sixthKotlin.config

import org.sopt.sixthKotlin.config.authentication.CustomAccessDeniedHandler
import org.sopt.sixthKotlin.config.authentication.CustomJwtAuthenticationEntryPoint
import org.sopt.sixthKotlin.config.filter.JwtAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableWebSecurity //web Security를 사용할 수 있게
class SecurityConfig(
    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
    private val customJwtAuthenticationEntryPoint: CustomJwtAuthenticationEntryPoint,
    private val customAccessDeniedHandler: CustomAccessDeniedHandler
) {

    @Bean
    @Profile("!prod")
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf { obj: CsrfConfigurer<HttpSecurity> -> obj.disable() } //csrf 공격을 대비하기 위한 csrf 토큰 disable 하기
            .formLogin { obj: FormLoginConfigurer<HttpSecurity> -> obj.disable() } //form login 비활성화 jwt를 사용하고 있으므로 폼 기반 로그인은 필요하지 않다.
            .httpBasic { obj: HttpBasicConfigurer<HttpSecurity> -> obj.disable() } //http 기본 인증은 사용자 이름과 비밀번호를 평문으로 전송하기 때문에 보안적으로 취약, 기본 인증을 비활성화 하고 있음
            .sessionManagement { session: SessionManagementConfigurer<HttpSecurity?> ->
                session.sessionCreationPolicy(
                    SessionCreationPolicy.STATELESS
                )
            } //session은
            .exceptionHandling { exception: ExceptionHandlingConfigurer<HttpSecurity?> ->
                exception.authenticationEntryPoint(customJwtAuthenticationEntryPoint)
                exception.accessDeniedHandler(customAccessDeniedHandler)
            }

        /*
        위에는 http의 특정 보안 구성을 비활성화하고, 인증 인가에 대한 예외를 처리하고 있다.
         */
        http.authorizeHttpRequests(Customizer { auth ->
            auth.requestMatchers(*AUTH_WHITELIST).permitAll()
            auth.anyRequest().authenticated()
        })
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
        /*
        UsernamePasswordAuthentication 클래스 앞에 jwtAuthenticationFilter를 등록한다.
         */return http.build()
    }

    @Bean
    fun corsConfigurer(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/**")
                    .allowedOrigins("*")
                    .allowedOriginPatterns("*")
                    .allowedMethods("*")
            }
        }
    } //cors에러를 대응하기 위한 메소드

    companion object {
        private val AUTH_WHITELIST = arrayOf(
            "api/users/sign-up",
            "api/users/sign-in"
        )
    }
}
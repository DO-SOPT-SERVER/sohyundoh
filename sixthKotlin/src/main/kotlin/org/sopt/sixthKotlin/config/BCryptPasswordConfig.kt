package org.sopt.sixthKotlin.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class BCryptPasswordConfig {
    @Bean
    fun bCryptPasswordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder(STRENGTH)
    }

    companion object {
        private const val STRENGTH = 10
    }
}
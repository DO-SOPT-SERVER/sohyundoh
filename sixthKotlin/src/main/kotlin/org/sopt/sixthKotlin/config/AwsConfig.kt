package org.sopt.sixthKotlin.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.SystemPropertyCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client

@Configuration
class AwsConfig(
    @param:Value("\${aws-property.access-key}") private val accessKey: String,
    @param:Value("\${aws-property.secret-key}") private val secretKey: String,
    @param:Value("\${aws-property.aws-region}") private val regionString: String
) {
    @Bean
    fun systemPropertyCredentialsProvider(): SystemPropertyCredentialsProvider {
        System.setProperty(AwsConfig.Companion.AWS_ACCESS_KEY_ID, accessKey)
        System.setProperty(AwsConfig.Companion.AWS_SECRET_ACCESS_KEY, secretKey)
        return SystemPropertyCredentialsProvider.create()
    }


    @Bean
    fun getRegion(): Region {
        return Region.of(regionString)
    }

    @Bean
    fun getS3Client(): S3Client {
        return S3Client.builder()
            .region(getRegion())
            .credentialsProvider(systemPropertyCredentialsProvider())
            .build()
    }

    companion object {
        private const val AWS_ACCESS_KEY_ID = "aws.accessKeyId"
        private const val AWS_SECRET_ACCESS_KEY = "aws.secretAccessKey"
    }
}
package org.sopt.sixthKotlin.external

import org.sopt.sixthKotlin.config.AwsConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest
import software.amazon.awssdk.services.s3.model.GetUrlRequest
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import java.io.IOException

import java.util.UUID

@Component
class S3Service(
    @param:Value("\${aws-property.s3-bucket-name}") private val bucketName: String,
    private val awsConfig: AwsConfig
) {
    @Throws(IOException::class)
    fun uploadImage(directoryPath: String, image: MultipartFile): String {
        val key = directoryPath + generateImageFileName()
        val s3Client = awsConfig.getS3Client()
        validateExtension(image)
        validateFileSize(image)
        val request = PutObjectRequest.builder()
            .bucket(bucketName)
            .key(key)
            .build()
        val requestBody = RequestBody.fromBytes(image.bytes)
        s3Client.putObject(request, requestBody)
        return s3Client.utilities().getUrl(GetUrlRequest.builder().bucket(bucketName).key(key).build()).toString()
    }

    @Throws(IOException::class)
    fun deleteImage(key: String?) {
        val s3Client = awsConfig.getS3Client()
        s3Client.deleteObject { builder: DeleteObjectRequest.Builder ->
            builder.bucket(
                bucketName
            )
                .key(key)
                .build()
        }
    }

    private fun validateExtension(image: MultipartFile) {
        val contentType = image.contentType
        if (!IMAGE_EXTENSIONS.contains(contentType)) {
            throw RuntimeException("이미지 확장자는 jpg, png, webp만 가능합니다.")
        }
    }

    private fun validateFileSize(image: MultipartFile) {
        if (image.size > MAX_FILE_SIZE) {
            throw RuntimeException("이미지 사이즈는 5MB를 넘을 수 없습니다.")
        }
    }

    private fun generateImageFileName(): String {
        return UUID.randomUUID().toString() + ".jpg"
    }

    companion object {
        private val IMAGE_EXTENSIONS: List<String> =
            arrayListOf("image/jpeg", "image/png", "image/jpg", "image/webp")
        private const val MAX_FILE_SIZE = 3 * 1024 * 1024L
    }
}
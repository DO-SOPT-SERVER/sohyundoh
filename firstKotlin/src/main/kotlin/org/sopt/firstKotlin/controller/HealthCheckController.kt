package org.sopt.firstKotlin.controller

import org.sopt.firstKotlin.domain.Person
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/health")
class HealthCheckController {
    @GetMapping("/api/v1")
    fun healthCheckV1() = "OK" // 이렇게 단일 표현식으로도 가능하다

    @GetMapping("/api/v2")
    fun healthCheckV2(): ResponseEntity<String> {
        return ResponseEntity.ok("OK")
    }

    @GetMapping("/api/v3")
    fun healthCheckV3(): ResponseEntity<Map<String, String>> {
        val response = HashMap<String, String>()
        response.put("status", "ok") //mc보이가 이미 = 을 사용했으니 난 Put으로 간다.
        return ResponseEntity.ok(response)
    }
    @GetMapping("/api/v4")
    fun healthCheckV4() : ResponseEntity<Person> {
        return ResponseEntity.ok(Person(lastName = "도", firstName = "소현", age = 23))
        //코틀린에서는 new 연산자를 사용하지 않는구나
    }

    @GetMapping("/api/v5")
    fun healthCheckV5() :HealthCheckResponse {
        return HealthCheckResponse("OK",true, "성공")
    }

    //데이터 클래스 사용 ! 클래스 내부에도 사용 가능하다.
    data class HealthCheckResponse(
        val status: String,
        val success: Boolean,
        val message: String
    )

}
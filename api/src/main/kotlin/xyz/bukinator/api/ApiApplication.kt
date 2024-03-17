package xyz.bukinator.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication(
    scanBasePackages = [
        "xyz.bukinator.api",
        "xyz.bukinator.house"
    ]
)
@Import(ApiConfiguration::class)
class ApiApplication

fun main(args: Array<String>) {
    runApplication<ApiApplication>(*args)
}

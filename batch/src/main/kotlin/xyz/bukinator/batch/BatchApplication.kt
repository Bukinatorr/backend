package xyz.bukinator.batch

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(
    scanBasePackages = [
        "xyz.bukinator.batch",
        "xyz.bukinator.house.*"
    ]
)
class BatchApplication

fun main(args: Array<String>) {
    runApplication<BatchApplication>(*args)
}

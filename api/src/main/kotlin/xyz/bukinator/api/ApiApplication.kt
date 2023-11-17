package xyz.bukinator.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BukinatorApiApplication

fun main(args: Array<String>) {
    runApplication<BukinatorApiApplication>(*args)
}

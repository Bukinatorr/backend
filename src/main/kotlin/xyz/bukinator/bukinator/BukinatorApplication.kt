package xyz.bukinator.bukinator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BukinatorApplication

fun main(args: Array<String>) {
	runApplication<BukinatorApplication>(*args)
}

package com.saltedfish.async

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@EnableAsync
@SpringBootApplication
class AsyncdemoApplication

fun main(args: Array<String>) {
	runApplication<AsyncdemoApplication>(*args)
}

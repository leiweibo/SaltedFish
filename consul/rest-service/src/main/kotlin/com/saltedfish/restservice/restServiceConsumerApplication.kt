package com.saltedfish.restservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class RestServiceApplication

fun main(args: Array<String>) {
	runApplication<RestServiceConsumerApplication>(*args)
}

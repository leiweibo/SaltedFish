package com.saltedfish.restservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class RestServiceConsumerApplication

fun main(args: Array<String>) {
	runApplication<RestServiceConsumerApplication>(*args)
}

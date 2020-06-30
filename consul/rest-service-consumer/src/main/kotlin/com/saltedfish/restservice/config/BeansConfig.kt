package com.saltedfish.restservice.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.http.client.SimpleClientHttpRequestFactory
import org.springframework.web.client.RestTemplate


@Configuration
class BeansConfig {
    @Autowired private lateinit var environment: Environment

    @Bean
    @LoadBalanced
    fun restTemplate(): RestTemplate? {
        val requestFactory = SimpleClientHttpRequestFactory()
        requestFactory.setReadTimeout(environment.getProperty("client.http.request.readTimeout", Int::class.java, 15000))
        requestFactory.setConnectTimeout(environment.getProperty("client.http.request.connectTimeout", Int::class.java, 3000))
        return RestTemplate(requestFactory)
    }
}
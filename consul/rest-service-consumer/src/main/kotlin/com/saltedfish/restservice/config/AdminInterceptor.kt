package com.saltedfish.restservice.config

import feign.RequestInterceptor
import feign.RequestTemplate
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration

@Configuration
class AdminInterceptor:RequestInterceptor {
    val logger: Logger = LoggerFactory.getLogger(AdminInterceptor::class.java)
    override fun apply(p0: RequestTemplate?) {
        p0?.header("Content-Type", "application/json")
        p0?.header("token", "123456")
        logger.debug("已进入Admin Interceptor拦截器之中")
    }
}
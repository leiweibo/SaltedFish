package com.saltedfish.restservice.config

import com.netflix.loadbalancer.IRule
import com.netflix.loadbalancer.RoundRobinRule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UserRibbonConfig {
    @Bean
    fun ribbonRule(): IRule {
        return RoundRobinRule()
    }
}
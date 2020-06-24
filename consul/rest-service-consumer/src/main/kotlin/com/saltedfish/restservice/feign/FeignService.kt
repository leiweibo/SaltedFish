package com.saltedfish.restservice.feign

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RequestMapping




@FeignClient("server-provider")
@Component
interface FeignService {
    @RequestMapping(value = ["/hello"])
    fun hello(): String? // 如果有参数, 必须用RequestParam注解

}
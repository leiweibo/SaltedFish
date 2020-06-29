package com.saltedfish.restservice.feign.fallback

import com.saltedfish.restservice.feign.FeignService
import org.springframework.stereotype.Component

@Component
class FeignFallback: FeignService {
    override fun hello(): String? {
        return "{code: 200, msg: 'hello msg', success: true}"
    }
}
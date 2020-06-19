package com.saltedfish.restservice.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloWorld {
    @RequestMapping("/")
    fun hello():String {
        return "HelloWorld."
    }
}
package com.saltedfish.restservice.controller

import com.saltedfish.restservice.model.Result
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class HelloWorld {

    private val loggr: Logger = LoggerFactory.getLogger(this.javaClass)

    @GetMapping("check")
    fun check(): String? {
        return "ok"
    }

    @RequestMapping("/hello")
    fun hello():Result {
        var result:Result = Result()
        result.success = true
        result.code = "200"
        result.msg = "HelloWrold."
        return result
    }
}
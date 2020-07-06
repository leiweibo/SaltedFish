package com.saltedfish.async.controller

import com.saltedfish.async.service.GatewayService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GatewayController {

    @Autowired
    lateinit var gatewayService: GatewayService

    @RequestMapping("/gateway/{userName}")
    fun gateWay(@PathVariable userName:String):String {
        val startTime = System.currentTimeMillis()
        for (i in 0..5) {
            gatewayService.getAlipayGateway(userName)
        }
        val endTime = System.currentTimeMillis()
        println("call gateway spent:${endTime - startTime} ms")
        return "call gateway spent: ${endTime - startTime} ms"
    }
}
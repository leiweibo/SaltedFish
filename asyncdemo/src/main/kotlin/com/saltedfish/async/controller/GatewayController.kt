package com.saltedfish.async.controller

import com.saltedfish.async.config.ExecutorConfig
import com.saltedfish.async.service.GatewayService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GatewayController {

    @Autowired
    lateinit var gatewayService: GatewayService

    @Autowired
    lateinit var executorConfig: ExecutorConfig

    @Autowired
    lateinit var context: ApplicationContext

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

    @RequestMapping("/gateway2/{userName}")
    fun gateWay2(@PathVariable userName:String):String {
        val startTime = System.currentTimeMillis()
        var future = gatewayService.getFuturedAlipayGateway(userName)
        try {
            Thread.sleep(5000)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        var endTime = System.currentTimeMillis()
        println("call gateway spent for: ${endTime - startTime}")
        return future.get()
    }

    @RequestMapping("/gateway3/{userName}")
    fun gateWay3(@PathVariable userName:String):String {
        val startTime = System.currentTimeMillis()
        for (i in 0..5) {
            gatewayService.getFuturedAlipayGateway2(userName)
        }
        val endTime = System.currentTimeMillis()
        println("call gateway spent:${endTime - startTime} ms")
        return "call gateway spent: ${endTime - startTime} ms"
    }

    @RequestMapping("/shutdown")
    fun shutdown():Boolean {
        println("Call the async thread pool shutdown method.")
        val t: ThreadPoolTaskExecutor = context.getBean("myExecutor") as ThreadPoolTaskExecutor
        val asyncShutdown = executorConfig.asyncShutDown(t)
        return asyncShutdown
    }
}
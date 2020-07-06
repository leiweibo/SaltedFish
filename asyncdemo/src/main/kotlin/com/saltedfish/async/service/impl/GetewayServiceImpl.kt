package com.saltedfish.async.service.impl

import com.saltedfish.async.service.GatewayService
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class GetewayServiceImpl: GatewayService {
    @Async
    override fun getAlipayGateway(userName: String) {
        println("Get start the gateway service, userName: $userName, threadName: ${Thread.currentThread().name}")
        val startTime = System.currentTimeMillis()
        try {
            Thread.sleep(5 * 1000);
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        var endTime = System.currentTimeMillis()
        print("Thread: ${Thread.currentThread().name}, spent: ${endTime - startTime}")
    }
}
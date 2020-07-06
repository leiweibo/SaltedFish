package com.saltedfish.async.service.impl

import com.saltedfish.async.service.GatewayService
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.AsyncResult
import org.springframework.stereotype.Service
import java.util.concurrent.Future

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

    @Async
    override fun getFuturedAlipayGateway(userName: String): Future<String> {
        lateinit var future: Future<String>
        println("Entered the get gateway service, userName: $userName")
        val startTime = System.currentTimeMillis()
        try {
            Thread.sleep(5000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
            future = AsyncResult<String>("error:${e.message}")
            return future
        }
        val endTime = System.currentTimeMillis()
        println("Thread: ${Thread.currentThread().name} spent:${endTime - startTime}")
        future = AsyncResult<String>("Thread:${Thread.currentThread().name}, spent ${endTime - startTime}")
        return future
    }
}
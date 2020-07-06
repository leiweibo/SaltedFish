package com.saltedfish.restservice

import com.saltedfish.restservice.config.UserRibbonConfig
import org.springframework.boot.runApplication
import org.springframework.cloud.client.SpringCloudApplication
import org.springframework.cloud.netflix.ribbon.RibbonClient
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.web.bind.annotation.RestController

@SpringCloudApplication
@RestController
@EnableFeignClients
@RibbonClient(name = "cloud-admin", configuration = [UserRibbonConfig::class])
class RestServiceConsumerApplication2 {
    fun main(args: Array<String>) {
	    runApplication<RestServiceConsumerApplication2>(*args)
    }
}
package com.saltedfish.restservice.controller

import com.saltedfish.restservice.feign.FeignService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.client.ServiceInstance
import org.springframework.cloud.client.discovery.DiscoveryClient
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import java.net.URI
import java.util.stream.Collectors


@RestController
class HelloWorldClient {

    @Autowired
    private val discoveryClient: DiscoveryClient? = null

    @Autowired
    private val loadBalancerClient: LoadBalancerClient? = null

//    通过这种方式，可以通过 val instance: ServiceInstance = loadBalancerClient!!.choose(SERVER_ID)
//    private val restTemplate: RestTemplate = RestTemplate()

//  通过这种方式，
    @Autowired
    private lateinit var restTemplate: RestTemplate

    private val SERVER_ID = "server-provider"

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    private val feignService: FeignService? = null

    @GetMapping("check")
    fun check(): String? {
        return "ok"
    }

    @GetMapping("uri")
    fun getServerUris(): List<URI?>? {
        return discoveryClient!!.getInstances(SERVER_ID)
                .stream()
                .map(ServiceInstance::getUri).collect(Collectors.toList())
    }

    // 通过loadBalance去获取
    @GetMapping("hello")
    fun hello(): String? {
//        val instance: ServiceInstance = loadBalancerClient!!.choose(SERVER_ID)
//        val url: String = instance.uri.toString().toString() + "/hello"
//        logger.info("remote server url：{}", url);
//        return restTemplate.getForObject(url, String::class.java)

        return restTemplate.getForObject("http://server-provider/hello", String::class.java)
    }

    // 通过feign访问服务
    @GetMapping(value = ["/feign-call"])
    fun feignCall(): String? {
        return feignService!!.hello()
    }

    // 通过ribbon(还有问题。)
    // -------------------------- ribbon --------------------------
    @GetMapping(value = ["/ribbon-call"])
    fun ribbonCall():String? {
        var method = "hello";
        return restTemplate.getForEntity("http://${SERVER_ID}/${method}", String::class.java).body
    }
}
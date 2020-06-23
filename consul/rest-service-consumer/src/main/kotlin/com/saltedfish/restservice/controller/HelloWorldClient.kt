package com.saltedfish.restservice.controller

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

    private val restTemplate: RestTemplate = RestTemplate()

    private val SERVER_ID = "server-provider"

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

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

    @GetMapping("hello")
    fun hello(): String? {
        val instance: ServiceInstance = loadBalancerClient!!.choose(SERVER_ID)
        val url: String = instance.uri.toString().toString() + "/hello"
        logger.info("remote server url：{}", url);
        return restTemplate.getForObject(url, String::class.java)
    }
}
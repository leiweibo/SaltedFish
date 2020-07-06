package com.saltedfish.async.service

import java.util.concurrent.Future

interface GatewayService {
    fun getAlipayGateway(userName: String)
    fun getFuturedAlipayGateway(userName: String):Future<String>
    fun getFuturedAlipayGateway2(userName: String)
}
package com.saltedfish.async.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.lang.Exception
import java.util.concurrent.Executor
import java.util.concurrent.ThreadPoolExecutor

@Configuration
@EnableAsync
class ExecutorConfig {
    @Bean
    fun myExecutor(): Executor {
        val executor: ThreadPoolTaskExecutor = ThreadPoolTaskExecutor()
        executor.corePoolSize = 5
        executor.maxPoolSize = 6
        executor.setQueueCapacity(9999)
        executor.setThreadNamePrefix("async-service-")
        executor.setRejectedExecutionHandler(ThreadPoolExecutor.CallerRunsPolicy())
        executor.initialize()
        return executor
    }

    fun asyncShutDown(executor: ThreadPoolTaskExecutor):Boolean {
        try {
            val poolSize = executor.poolSize
            println("Current async thread max pool size: $poolSize")
            executor.setWaitForTasksToCompleteOnShutdown(true)
            executor.setAwaitTerminationSeconds(6)
            executor.shutdown()
            val poolSize2 = executor.poolSize
            print("Current async thread max pool size: $poolSize2")
            return true;
        } catch (e: Exception) {
            return false
        }
    }
}
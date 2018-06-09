package com.org.onion

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@SpringBootApplication
class OnionWebApplication

fun main(args: Array<String>) {
    runApplication<OnionWebApplication>(*args)
}

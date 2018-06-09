package com.org.onion

import de.codecentric.boot.admin.server.config.EnableAdminServer
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@EnableAdminServer
@EnableEurekaServer
@SpringBootApplication
class OnionEurekaServiceApplication

fun main(args: Array<String>) {
    runApplication<OnionEurekaServiceApplication>(*args)
}

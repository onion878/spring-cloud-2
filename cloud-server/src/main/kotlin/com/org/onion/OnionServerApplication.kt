package com.org.onion

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@MapperScan("com.org.onion.mapper")
class OnionServerApplication

fun main(args: Array<String>) {
    runApplication<OnionServerApplication>(*args)
}

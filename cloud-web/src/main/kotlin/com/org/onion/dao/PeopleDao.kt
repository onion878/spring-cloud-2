package com.org.onion.dao

import org.slf4j.LoggerFactory
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestMapping



@FeignClient(name = "cloud-server", path = "/people", fallback = PeopleDao.PeopleFallBack::class)
interface PeopleDao {

    @RequestMapping(value = "/listAllMy", method = [(RequestMethod.POST)])
    fun getAll(): MutableList<Map<String, Any>>

    @Component
    class PeopleFallBack: PeopleDao {

        private val logger = LoggerFactory.getLogger(PeopleFallBack::class.java)

        override fun getAll(): MutableList<Map<String, Any>> {
            val msg = "调用接口getAll失败"
            logger.error("$msg")
            throw RuntimeException("$msg")
        }
    }
}
package com.org.onion.controller

import com.org.onion.dao.PeopleDao
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController(val peopleDao: PeopleDao) {

    @RequestMapping("/hello")
    fun index() = "hello world"

    @RequestMapping("/getAll")
    fun getAll() = peopleDao.getAll()
}
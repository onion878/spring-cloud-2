package com.org.onion.controller

import com.org.onion.model.People
import com.org.onion.service.PeopleService
import onion.com.tgb.dao.BaseService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/people")
class PeopleController(val peopleService: PeopleService): BaseController<People, Long>() {
    override fun getService(): BaseService<People, Long> = peopleService

    @ResponseBody
    @PostMapping("/listAllMy")
    fun listAllMy() = peopleService.listAllMy()

    @ResponseBody
    @PostMapping()
    fun saveMy(@RequestBody people: People) = peopleService.saveMy(people)
}
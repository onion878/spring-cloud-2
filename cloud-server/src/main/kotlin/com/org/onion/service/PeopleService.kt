package com.org.onion.service

import com.org.onion.mapper.PeopleDao
import com.org.onion.model.People
import onion.com.tgb.dao.BaseService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class PeopleService(val peopleDao: PeopleDao): BaseService<People, Long>() {
    fun listAllMy() = peopleDao.getAll()

    @Transactional
    fun saveMy(people: People) = peopleDao.save(people)
}
package com.org.onion.mapper

import com.org.onion.model.People
import org.apache.ibatis.annotations.Param

interface PeopleDao{
    fun getOne(@Param("id") id: Int): People

    fun save(people: People)

    fun getAll(): List<People>
}
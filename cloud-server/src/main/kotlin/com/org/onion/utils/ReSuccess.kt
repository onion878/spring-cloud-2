package com.onion.utils

import com.fasterxml.jackson.databind.ObjectMapper
import java.util.HashMap

private val jsonMapper = ObjectMapper()

@Throws(Exception::class)
fun toSuccess(value: String, data: Any?): Any {
    val jsonMap = HashMap<String, Any>()
    jsonMap["success"] = true
    jsonMap["val"] = value
    if (data != null) {
        jsonMap["data"] = data
    } else {
        jsonMap["data"] = "没有返回数据";
    }
    return jsonMap
}

@Throws(Exception::class)
fun toFail(msg: String): String {
    val jsonMap = HashMap<String, Any>()
    jsonMap["success"] = false
    jsonMap["msg"] = msg
    return jsonMapper.writeValueAsString(jsonMap)
}

@Throws(Exception::class)
fun toString(jsonMap: Any): String {
    return jsonMapper.writeValueAsString(jsonMap)
}

@Throws(Exception::class)
fun toList(rows: List<Any>, total: Long): Any {
    val jsonMap = HashMap<String, Any>()
    jsonMap["rows"] = rows
    jsonMap["total"] = total
    return jsonMap
}
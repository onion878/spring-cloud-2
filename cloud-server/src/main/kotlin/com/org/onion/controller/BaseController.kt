package com.org.onion.controller

import com.onion.utils.toSuccess
import onion.com.tgb.dao.BaseService
import onion.com.tgb.model.Page
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.io.Serializable

abstract class BaseController<T, in PK: Serializable> {
    abstract fun getService(): BaseService<T, PK>

    @ResponseBody
    @PostMapping("/listByPage")
    open fun listByPage(@RequestBody page: Page) = getService().listByPage(page)

    @ResponseBody
    @PostMapping("/listByEntity")
    open fun listByEntity(@RequestBody entity: T) = getService().listByEntity(entity)

    @ResponseBody
    @PostMapping("/SaveOrUpdate")
    @Throws(Exception::class)
    open fun SaveOrUpdate(@RequestBody t: T) = toSuccess("操作成功!", getService().saveOrUpdate(t))

    @ResponseBody
    @PostMapping("/SaveOrUpdateList")
    @Throws(Exception::class)
    open fun SaveOrUpdateList(@RequestBody ts: MutableList<T>): Any? {
        getService().saveOrUpdateList(ts)
        return toSuccess("操作成功!", ts.size)
    }

    @ResponseBody
    @PostMapping("/save")
    @Throws(Exception::class)
    open fun save(@RequestBody t: T) = toSuccess("保存成功!", getService().save(t))

    @ResponseBody
    @PostMapping("/update")
    @Throws(Exception::class)
    open fun update(@RequestBody t: T) = toSuccess("修改成功!", getService().update(t))

    @ResponseBody
    @PostMapping("/updateField")
    @Throws(Exception::class)
    open fun updateField(@RequestBody t: T) = toSuccess("修改成功!", getService().updateField(t))

    @ResponseBody
    @PostMapping("/delete")
    @Throws(Exception::class)
    open fun delete(@RequestBody t: T) = toSuccess("删除成功!", getService().delete(t))

    @ResponseBody
    @PostMapping("/deleteAll")
    @Throws(Exception::class)
    open fun deleteAll(@RequestBody ts: MutableList<T>): Any? {
        getService().deleteAll(ts)
        return toSuccess("删除成功!", ts.size)
    }

    @ResponseBody
    @RequestMapping("/findById/{id}")
    @Throws(Exception::class)
    open fun findById(@PathVariable id: PK) = getService().findById(id)

    @ResponseBody
    @RequestMapping("/findAll")
    @Throws(Exception::class)
    open fun findAll() = toSuccess("", getService().findAll())

}
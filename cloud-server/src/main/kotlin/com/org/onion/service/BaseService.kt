package onion.com.tgb.dao

import onion.com.tgb.model.Page
import org.hibernate.SessionFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.orm.hibernate5.support.HibernateDaoSupport
import org.springframework.transaction.annotation.Transactional
import java.io.Serializable
import java.lang.reflect.ParameterizedType
import java.util.HashMap
import javax.persistence.EntityManager

open class BaseService<T, in PK: Serializable>: HibernateDaoSupport() {

    @Autowired
    open val entityManager: EntityManager ?= null

    @Autowired
    fun initSession(sessionFactory: SessionFactory) {
        setSessionFactory(sessionFactory)
    }

    var clazz = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<T>

    @Transactional
    open fun save(entity: T):T {
        hibernateTemplate?.save(entity!!)
        return entity
    }

    open fun findById(id: PK) = hibernateTemplate?.get(clazz, id)

    @Transactional
    open fun update(entity: T):T {
        hibernateTemplate?.update(entity!!)
        return entity
    }

    @Transactional
    open fun update(hql: String) {
        val session = sessionFactory?.currentSession
        val query = session?.createQuery(hql)
        query?.executeUpdate()
    }

    @Transactional
    open fun updateField(entity: T): Any? {
        val session = sessionFactory?.currentSession
        return session?.merge(entity)
    }

    @Transactional
    open fun delete(hql: String) {
        val session = sessionFactory?.currentSession
        val query = session?.createQuery(hql)
        query?.executeUpdate()
    }

    @Transactional
    open fun insert(hql: String) {
        val session = sessionFactory?.currentSession
        val query = session?.createQuery(hql)
        query?.executeUpdate()
    }

    open fun selectAll(hql: String): MutableList<T> {
        val session = sessionFactory?.currentSession
        val list = session?.createQuery(hql)?.list()
        return list as MutableList<T>
    }

    @Transactional
    open fun saveOrUpdate(entity: T):T {
        hibernateTemplate?.saveOrUpdate(entity!!)
        return entity
    }

    @Transactional
    open fun saveOrUpdateList(entities: MutableList<T>) {
        for (t in entities) {
            hibernateTemplate?.saveOrUpdate(t!!)
        }
    }

    @Transactional
    open fun delete(entity: T):T {
        hibernateTemplate?.delete(entity!!)
        return entity
    }

    @Transactional
    open fun deleteAll(entities: MutableList<T>) {
        hibernateTemplate?.deleteAll(entities)
    }

    open fun findAll() = entityManager?.createQuery("from ${clazz.name}")?.resultList as MutableList<T>

    open fun listByPage(page: Page): Map<String, Any> {
        val map = HashMap<String, Any>()
        map["total"] = getTotal(page)
        map["rows"] = getAll(page)
        map["page"] = page.page
        map["size"] = page.rows
        return map
    }

    open fun getAll(page: Page): MutableList<T> {
        val session = sessionFactory?.currentSession
        var hql = "from " + clazz.name + " "
        if (page.value != null && page.value.trim { it <= ' ' } != "")
            hql = hql + " where " + page.search + " LIKE ? "
        if (page.order != null && page.order.trim { it <= ' ' } != "")
            hql = hql + page.order + " "
        val q = session?.createQuery(hql)
        if (page.value != null && page.value.trim { it <= ' ' } != "")
            q?.setParameter(0, "%" + page.value + "%")
        q?.firstResult = (page.page - 1) * page.rows
        q?.maxResults = page.rows
        return q?.list() as MutableList<T>
    }

    open fun listByEntity(entity: T) = hibernateTemplate?.findByExample(entity)

    open fun getTotal(page: Page): Long {
        val session = sessionFactory?.currentSession
        var hql = "select count(0) from " + clazz.name + " "
        if (page.value != null && page.value.trim { it <= ' ' } != "")
            hql = hql + " where " + page.search + " LIKE ? "
        val q = session?.createQuery(hql)
        if (page.value != null && page.value.trim { it <= ' ' } != "")
            q?.setParameter(0, "%" + page.value + "%")
        return java.lang.Long.valueOf(q?.uniqueResult().toString())
    }

    open fun findAllByHql(hql: String, vararg args: Any): MutableList<T> {
        val session = sessionFactory?.currentSession
        val q = session?.createQuery(hql)
        for (i in args.indices) {
            q?.setParameter(i, args[i])
        }
        return q?.list() as MutableList<T>
    }

    open fun listByHql(hql: String, page: Page): Map<String, Any?> {
        val map = HashMap<String, Any?>()
        val session = sessionFactory?.currentSession
        val q = session?.createQuery(hql)
        q?.firstResult = (page.page - 1) * page.rows
        q?.maxResults = page.rows
        map["rows"] = q?.list()
        map["total"] = q?.list()?.size
        return map
    }

    open fun getUniqueAny(hql: String, vararg args: Any): Any {
        val session = sessionFactory?.currentSession
        val query = session?.createQuery(hql)
        if (args != null) {
            for (i in args.indices) {
                query?.setParameter(i, args[i])
            }
        }
        return query?.uniqueResult() as Any
    }

    @Transactional
    open fun deleteByHql(hql: String) {
        val session = sessionFactory?.currentSession
        val query = session?.createQuery(hql)
        query?.executeUpdate()
    }

}
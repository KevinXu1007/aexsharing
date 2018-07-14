package com.aexsharing.web.dao.base;

import com.aexsharing.core.base.BaseModel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author : Kevin Xu
 * github: https://github.com/kevinxu1007
 * email: kevinxu1007@gmail.com
 * Date: 2018/7/16 15:38
 **/
@Repository
@Transactional(rollbackFor = {})
public class CommonDao {

    @PersistenceContext
    private EntityManager entityManager;

    public <T extends BaseModel> T get(Class<T> type, long id) {
        return entityManager.find(type, id);
    }

    public <T extends BaseModel> T update(T entity) {
        return entityManager.merge(entity);
    }

    public <T extends BaseModel> void save(T entity) {
        entityManager.persist(entity);
    }

    public <T extends BaseModel> void delete(T entity) {
        entityManager.remove(entity);
    }

    public List getAll(Class<? extends BaseModel> tableClass) {
        Query query = entityManager.createQuery("from " + tableClass.getSimpleName());
        return query.getResultList();
    }


}
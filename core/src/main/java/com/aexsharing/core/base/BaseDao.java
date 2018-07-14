package com.aexsharing.core.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * @author : Kevin Xu
 * Date: 2018/6/8
 */

@Repository
public interface BaseDao<T> extends JpaRepository<T, Long> {

    /**
     * 根据名字查
     *
     * @param name
     * @return
     */
    T findByName(String name);

    /**
     * 删除
     *
     * @param name
     * @return
     */
    boolean deleteByName(String name);
}

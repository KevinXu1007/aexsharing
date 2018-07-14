package com.aexsharing.web.dao;

import com.aexsharing.web.model.ChangeLogModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : Kevin Xu
 * github: https://github.com/kevinxu1007
 * email: kevinxu1007@gmail.com
 * Date: 2018/7/16 15:38
 **/
@Repository
public interface ChangeLogDao extends JpaRepository<ChangeLogModel, Long> {

    /**
     * find
     *
     * @param name
     * @return
     */
    ChangeLogModel findByName(String name);

}

package com.aexsharing.web.dao;

import com.aexsharing.web.model.TechnologyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : Kevin Xu
 * github: https://github.com/kevinxu1007
 * email: kevinxu1007@gmail.com
 * Date: 2018/7/16 15:38
 **/
@Repository
public interface TechnologyDao extends JpaRepository<TechnologyModel, Long> {

    /**
     * 根据名字查
     *
     * @param name
     * @return
     */
    TechnologyModel findTechnologyByName(String name);


}

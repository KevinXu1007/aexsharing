package com.aexsharing.web.dao;

import com.aexsharing.web.model.LinkModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : Kevin Xu
 * github: https://github.com/kevinxu1007
 * email: kevinxu1007@gmail.com
 * Date: 2018/7/16 15:38
 **/
@Repository
public interface LinkDao extends JpaRepository<LinkModel, Long> {

    /**
     * 根据名字查友链
     *
     * @param name
     * @return
     */
    LinkModel findLinkByName(String name);

}

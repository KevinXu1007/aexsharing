package com.aexsharing.web.service;


import com.aexsharing.web.model.TechnologyModel;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author : Kevin Xu
 * github: https://github.com/kevinxu1007
 * email: kevinxu1007@gmail.com
 * Date: 2018/7/16 15:38
 **/

public interface TechnologyService {

    /**
     * find all
     *
     * @return
     */
    List<TechnologyModel> findAll();

    /**
     * find all
     *
     * @param start
     * @param pageSize
     * @return
     */
    Page<TechnologyModel> findAll(int start, int pageSize);

    /**
     * find
     *
     * @param id
     * @return
     */
    TechnologyModel findById(Long id);

    /**
     * find
     *
     * @param name
     * @return
     */
    TechnologyModel findByName(String name);

    /**
     * update
     *
     * @param model
     * @return
     */
    TechnologyModel update(TechnologyModel model);

    /**
     * add
     *
     * @param model
     * @return
     */
    TechnologyModel add(TechnologyModel model);

    /**
     * del
     *
     * @param id
     */
    void del(Long id);

}

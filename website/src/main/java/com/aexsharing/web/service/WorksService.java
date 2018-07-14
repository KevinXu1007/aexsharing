package com.aexsharing.web.service;


import com.aexsharing.web.model.WorksModel;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author : Kevin Xu
 * github: https://github.com/kevinxu1007
 * email: kevinxu1007@gmail.com
 * Date: 2018/7/16 15:38
 **/

public interface WorksService {

    /**
     * find all
     *
     * @return
     */
    List<WorksModel> findAll();

    /**
     * find all page
     *
     * @param start
     * @param pageSize
     * @return
     */
    Page<WorksModel> findAll(int start, int pageSize);

    /**
     * find
     *
     * @param id
     * @return
     */
    WorksModel findById(Long id);

    /**
     * find
     *
     * @param name
     * @return
     */
    WorksModel findByName(String name);

    /**
     * update
     *
     * @param model
     * @return
     */
    WorksModel update(WorksModel model);


    /**
     * add
     *
     * @param model
     * @return
     */
    WorksModel add(WorksModel model);


    /**
     * del
     *
     * @param id
     */
    void del(Long id);
}

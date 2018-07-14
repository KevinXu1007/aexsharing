package com.aexsharing.web.service;

import com.aexsharing.web.model.ChangeLogModel;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author : Kevin Xu
 * github: https://github.com/kevinxu1007
 * email: kevinxu1007@gmail.com
 * Date: 2018/7/16 15:38
 **/
public interface ChangeLogService {

    /**
     * 根据id查
     *
     * @param id
     * @return
     */
    ChangeLogModel findById(Long id);

    /**
     * 根据名字查
     *
     * @param name
     * @return
     */
    ChangeLogModel findByName(String name);

    /**
     * find all
     *
     * @param start
     * @param pageSize
     * @return
     */
    Page<ChangeLogModel> findAll(int start, int pageSize);

    /**
     * find all
     *
     * @return
     */
    List<ChangeLogModel> findAll();

    /**
     * add
     *
     * @param model
     * @return
     */
    ChangeLogModel add(ChangeLogModel model);

    /**
     * update
     *
     * @param model
     * @return
     */
    ChangeLogModel update(ChangeLogModel model);

    /**
     * delete
     *
     * @param id
     * @return
     */
    ChangeLogModel delete(Long id);
}

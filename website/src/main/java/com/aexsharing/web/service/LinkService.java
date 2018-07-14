package com.aexsharing.web.service;


import com.aexsharing.web.model.LinkModel;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author : Kevin Xu
 * github: https://github.com/kevinxu1007
 * email: kevinxu1007@gmail.com
 * Date: 2018/7/16 15:38
 **/
public interface LinkService {

    /**
     * 根据id查友链
     *
     * @param id
     * @return
     */
    LinkModel findById(Long id);

    /**
     * 根据名字查友链
     *
     * @param name
     * @return
     */
    LinkModel findByName(String name);

    /**
     * 分页查
     *
     * @param start
     * @param pageSize
     * @return
     */
    Page<LinkModel> findAll(int start, int pageSize);

    /**
     * 查所有
     *
     * @return
     */
    List<LinkModel> findAll();

    /**
     * 添加
     *
     * @param model
     * @return
     */
    LinkModel add(LinkModel model);

    /**
     * 更新
     *
     * @param model
     * @return
     */
    LinkModel update(LinkModel model);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    LinkModel delete(Long id);

}

package com.aexsharing.web.service;

import com.aexsharing.web.model.AdminModel;
import com.aexsharing.core.exception.UserNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author : Kevin Xu
 * github: https://github.com/kevinxu1007
 * email: kevinxu1007@gmail.com
 * Date: 2018/7/16 15:38
 **/
public interface AdminUserService {

    /**
     * 根据用户名查用户
     *
     * @param userName
     * @return
     */
    AdminModel findAdminUserByUserName(String userName);

    /**
     * 根据id查用户
     *
     * @param id
     * @return
     */
    AdminModel findAdminUserById(Long id);

    /**
     * 添加用户
     *
     * @param model
     * @return
     */
    AdminModel addAdminUser(AdminModel model);

    /**
     * 更新用户
     *
     * @param model
     * @return
     * @throws UserNotFoundException
     */
    AdminModel updateAdminUser(AdminModel model) throws UserNotFoundException;

    /**
     * 获取分页
     *
     * @param start
     * @param pageSize
     * @return
     */
    Page<AdminModel> getAdminUsers(int start, int pageSize);

    /**
     * 查所有
     *
     * @return
     */
    List<AdminModel> getAdminUsers();

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws UserNotFoundException
     */
    AdminModel deleteAdminUserById(Long id) throws UserNotFoundException;

    /**
     * 禁
     *
     * @param id
     * @return
     * @throws UserNotFoundException
     */
    AdminModel forbidAdminUserById(Long id) throws UserNotFoundException;

}

package com.aexsharing.web.service;


import com.aexsharing.web.model.UserModel;
import com.aexsharing.core.exception.UserNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

/**
 * @author : Kevin Xu
 * github: https://github.com/kevinxu1007
 * email: kevinxu1007@gmail.com
 * Date: 2018/7/16 15:38
 **/
public interface UserService {
    /**
     * 根据id查用户
     *
     * @param id id
     * @return UserModel
     */
    Optional<UserModel> findUserById(Long id);

    /**
     * 根据邮件查用户
     *
     * @param email email
     * @return UserModel
     */
    UserModel findUserByEmail(String email);

    /**
     * 添加用户
     *
     * @param model model
     * @return UserModel
     */
    UserModel addUser(UserModel model);

    /**
     * 更新用户
     *
     * @param model model
     * @return UserModel
     * @throws UserNotFoundException UserNotFoundException
     */
    UserModel updateUser(UserModel model) throws UserNotFoundException;

    /**
     * 查找所有 带分页
     *
     * @param start    start
     * @param pageSize pageSize
     * @return Page
     */
    Page<UserModel> findAll(int start, int pageSize);

    /**
     * 查找所有 不带分页
     *
     * @return List
     */
    List<UserModel> findAll();

    /**
     * 删除用户
     *
     * @param id id
     * @return UserModel
     * @throws UserNotFoundException
     */
    UserModel deleteUserById(Long id) throws UserNotFoundException;

}

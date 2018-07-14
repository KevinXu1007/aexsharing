package com.aexsharing.web.dao;

import com.aexsharing.web.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : Kevin Xu
 * github: https://github.com/kevinxu1007
 * email: kevinxu1007@gmail.com
 * Date: 2018/7/16 15:38
 **/

@Repository
public interface UserDao extends JpaRepository<UserModel, Long> {

    /**
     * 根据邮件查
     *
     * @param email
     * @return
     */
    UserModel findUserByEmail(String email);
}

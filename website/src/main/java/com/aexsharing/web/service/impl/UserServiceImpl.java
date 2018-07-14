package com.aexsharing.web.service.impl;

import com.aexsharing.web.dao.UserDao;
import com.aexsharing.web.model.UserModel;
import com.aexsharing.core.exception.UserNotFoundException;
import com.aexsharing.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author : Kevin Xu
 * github: https://github.com/kevinxu1007
 * email: kevinxu1007@gmail.com
 * Date: 2018/7/16 15:38
 **/
@Service
public class UserServiceImpl implements UserService {

    private final UserDao dao;

    @Autowired
    public UserServiceImpl(UserDao dao) {
        this.dao = dao;
    }

    @Override
    public Optional<UserModel> findUserById(Long id) {
        return dao.findById(id);
    }

    @Override
    public UserModel findUserByEmail(String email) {
        return dao.findUserByEmail(email);
    }

    @Override
    public UserModel addUser(UserModel model) {
        model.setCreateTime(new Date());
        model.setUpdateTime(new Date());
        return dao.save(model);
    }

    @Override
    public UserModel updateUser(UserModel model) throws UserNotFoundException {
        UserModel userUpdate = dao.findUserByEmail(model.getEmail());
        if (userUpdate == null) {
            throw new UserNotFoundException();
        }
        if (model.getAddress() != null) {
            userUpdate.setAddress(model.getAddress());
        }
        if (model.getEmail() != null) {
            userUpdate.setEmail(model.getEmail());
        }
        if (model.getGender() != 0) {
            userUpdate.setGender(model.getGender());
        }
        if (model.getImgUrl() != null) {
            userUpdate.setImgUrl(model.getImgUrl());
        }
        if (model.getNickName() != null) {
            userUpdate.setNickName(model.getNickName());
        }
        if (model.getPhone() != 0) {
            userUpdate.setPhone(model.getPhone());
        }
        userUpdate.setUpdateTime(new Date());
        dao.save(userUpdate);
        return userUpdate;
    }

    @Override
    public Page<UserModel> findAll(int start, int pageSize) {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        return dao.findAll(PageRequest.of(start - 1, pageSize, sort));
    }

    @Override
    public List<UserModel> findAll() {
        return dao.findAll();
    }

    @Override
    public UserModel deleteUserById(Long id) throws UserNotFoundException {
        Optional<UserModel> modelOptional = dao.findById(id);
        if (!modelOptional.isPresent()) {
            throw new UserNotFoundException();
        }
        UserModel userModel = modelOptional.get();
        dao.delete(userModel);
        return userModel;
    }

}

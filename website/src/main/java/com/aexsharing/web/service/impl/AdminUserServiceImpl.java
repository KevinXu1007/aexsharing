package com.aexsharing.web.service.impl;

import com.aexsharing.web.dao.AdminUserDao;
import com.aexsharing.web.model.AdminModel;
import com.aexsharing.core.exception.UserNotFoundException;
import com.aexsharing.web.service.AdminUserService;
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
public class AdminUserServiceImpl implements AdminUserService {

    private final AdminUserDao dao;

    @Autowired
    public AdminUserServiceImpl(AdminUserDao dao) {
        this.dao = dao;
    }

    @Override
    public AdminModel findAdminUserByUserName(String userName) {
        return dao.findAdminUserByUserName(userName);
    }

    @Override
    public AdminModel findAdminUserById(Long id) {
        Optional<AdminModel> optionalModel = dao.findById(id);
        return optionalModel.orElse(null);
    }

    @Override
    public AdminModel addAdminUser(AdminModel model) {
        model.setCreateTime(new Date());
        model.setUpdateTime(new Date());
        return dao.save(model);

    }

    @Override
    public AdminModel updateAdminUser(AdminModel model) throws UserNotFoundException {
        Optional<AdminModel> optionalModel = dao.findById(model.getId());
        if (!optionalModel.isPresent()) {
            throw new UserNotFoundException();
        }
        AdminModel adminModel = optionalModel.get();
        if (model.getPassword() != null) {
            adminModel.setPassword(model.getPassword());
        }
        if (model.getUserName() != null) {
            adminModel.setUserName(model.getUserName());
        }
        adminModel.setUpdateTime(new Date());
        return dao.save(adminModel);
    }

    @Override
    public Page<AdminModel> getAdminUsers(int start, int pageSize) {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        return dao.findAll(PageRequest.of(start - 1, pageSize, sort));
    }

    @Override
    public AdminModel deleteAdminUserById(Long id) throws UserNotFoundException {
        Optional<AdminModel> optionalModel = dao.findById(id);
        if (!optionalModel.isPresent()) {
            throw new UserNotFoundException();
        }
        AdminModel adminModel = optionalModel.get();
        dao.delete(adminModel);
        return adminModel;
    }

    @Override
    public AdminModel forbidAdminUserById(Long id) throws UserNotFoundException {
        Optional<AdminModel> optionalModel = dao.findById(id);
        if (!optionalModel.isPresent()) {
            throw new UserNotFoundException();
        }
        AdminModel adminModel = optionalModel.get();
        adminModel.setStatus(2);
        return dao.save(adminModel);
    }

    @Override
    public List<AdminModel> getAdminUsers() {
        return dao.findAll();
    }
}

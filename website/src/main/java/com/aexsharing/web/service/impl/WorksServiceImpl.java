package com.aexsharing.web.service.impl;

import com.aexsharing.web.dao.WorksDao;
import com.aexsharing.web.model.WorksModel;
import com.aexsharing.web.service.WorksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
public class WorksServiceImpl implements WorksService {

    private WorksDao dao;

    @Autowired
    public WorksServiceImpl(WorksDao dao) {
        this.dao = dao;
    }

    @Override
    public List<WorksModel> findAll() {
        return dao.findAll();
    }

    @Override
    public Page<WorksModel> findAll(int start, int pageSize) {
        return dao.findAll(PageRequest.of(start - 1, pageSize));
    }

    @Override
    public WorksModel findById(Long id) {
        Optional<WorksModel> optionalModel = dao.findById(id);
        return optionalModel.orElse(null);
    }

    @Override
    public WorksModel findByName(String name) {
        return dao.findWorkByName(name);
    }

    @Override
    public WorksModel update(WorksModel model) {
        Optional<WorksModel> optional = dao.findById(model.getId());
        if (!optional.isPresent()) {
            return null;
        }
        WorksModel result = optional.get();
        if ("".equals(model.getCompleteTime()) && model.getCompleteTime() != null) {
            result.setCompleteTime(model.getCompleteTime());
        }
        if ("".equals(model.getImgUrl()) && model.getImgUrl() != null) {
            result.setImgUrl(model.getImgUrl());
        }
        if ("".equals(model.getName()) && model.getName() != null) {
            result.setName(model.getName());
        }
        if ("".equals(model.getSummary()) && model.getSummary() != null) {
            result.setSummary(model.getSummary());
        }
        if ("".equals(model.getUrl()) && model.getUrl() != null) {
            result.setUrl(model.getUrl());
        }
        result.setUpdateTime(new Date());
        return dao.save(result);
    }

    @Override
    public WorksModel add(WorksModel model) {
        model.setCreateTime(new Date());
        model.setUpdateTime(new Date());
        return dao.save(model);
    }

    @Override
    public void del(Long id) {
        Optional<WorksModel> optional = dao.findById(id);
        if (!optional.isPresent()) {
            return;
        }
        dao.delete(optional.get());
    }
}

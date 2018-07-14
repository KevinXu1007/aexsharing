package com.aexsharing.web.service.impl;

import com.aexsharing.web.dao.LinkDao;
import com.aexsharing.web.model.LinkModel;
import com.aexsharing.web.service.LinkService;
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
public class LinkServiceImpl implements LinkService {

    private final LinkDao dao;

    @Autowired
    public LinkServiceImpl(LinkDao dao) {
        this.dao = dao;
    }

    @Override
    public LinkModel findById(Long id) {
        Optional<LinkModel> optionalModel = dao.findById(id);
        return optionalModel.orElse(null);
    }

    @Override
    public LinkModel findByName(String name) {
        return dao.findLinkByName(name);
    }

    @Override
    public Page<LinkModel> findAll(int start, int pageSize) {
        Sort sort = new Sort(Sort.Direction.DESC, "order");
        return dao.findAll(PageRequest.of(start - 1, pageSize, sort));
    }

    @Override
    public List<LinkModel> findAll() {
        return dao.findAll();
    }

    @Override
    public LinkModel add(LinkModel model) {
        model.setCreateTime(new Date());
        model.setUpdateTime(new Date());
        return dao.save(model);
    }

    @Override
    public LinkModel update(LinkModel model) {
        Optional<LinkModel> optional = dao.findById(model.getId());
        if (!optional.isPresent()) {
            return null;
        }
        LinkModel linkModel = optional.get();
        if (model.getName() != null) {
            linkModel.setName(model.getName());
        }
        if (model.getUrl() != null) {
            linkModel.setUrl(model.getUrl());
        }
        model.setUpdateTime(new Date());
        return dao.save(linkModel);
    }

    @Override
    public LinkModel delete(Long id) {
        Optional<LinkModel> optional = dao.findById(id);
        if (!optional.isPresent()) {
            return null;
        }
        LinkModel linkModel = optional.get();
        dao.delete(linkModel);
        return linkModel;
    }
}

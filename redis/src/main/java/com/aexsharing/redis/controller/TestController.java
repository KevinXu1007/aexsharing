package com.aexsharing.redis.controller;

import com.aexsharing.core.base.Result;
import com.aexsharing.redis.dao.CommonRedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Kevin Xu
 * Date: 2018/6/26
 */

@RestController
@RequestMapping("/redis")
public class TestController {

    private final CommonRedisDao dao;

    @Autowired
    public TestController(CommonRedisDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "get/{key}", method = RequestMethod.GET)
    public Result<String> find(@PathVariable("key") String key) {
        String value = dao.getValue(key);
        return new Result<>(value);
    }

    @RequestMapping(value = "add/{key}/{value}", method = RequestMethod.GET)
    public Result<Boolean> add(@PathVariable("value") String value, @PathVariable("key") String key) {
        return new Result<>(dao.cacheValue(key, value));
    }

    @RequestMapping(value = "del/{key}", method = RequestMethod.GET)
    public Result<Boolean> del(@PathVariable("key") String key) {
        return new Result<>(dao.removeValue(key));
    }

    @RequestMapping(value = "count/{key}", method = RequestMethod.GET)
    public Result<Long> count(@PathVariable("key") String key) {
        return new Result<>(dao.getListSize(key));
    }


}

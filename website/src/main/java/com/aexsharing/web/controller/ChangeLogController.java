package com.aexsharing.web.controller;

import com.aexsharing.web.model.ChangeLogModel;
import com.aexsharing.core.base.BaseController;
import com.aexsharing.core.base.Result;
import com.aexsharing.core.constant.CodeConst;
import com.aexsharing.web.service.ChangeLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : Kevin Xu
 * github: https://github.com/kevinxu1007
 * email: kevinxu1007@gmail.com
 * Date: 2018/7/16 15:38
 **/
@RestController
@RequestMapping("/changeLog")
@Api(value = "更新日志相关api", description = "更新日志相关api")
public class ChangeLogController extends BaseController {

    private final ChangeLogService service;

    @Autowired
    public ChangeLogController(ChangeLogService service) {
        this.service = service;
    }

    /**
     * 通过id查找
     *
     * @param id id
     * @return model
     */
    @Override
    @RequestMapping(value = "findById/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "通过id查找", notes = "通过id查找", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一id", required = true, dataType = "Long", paramType = "path")
    })
    public Result findById(@PathVariable("id") Long id) {
        ChangeLogModel changeLogModel = service.findById(id);
        if (changeLogModel == null) {
            return new Result(CodeConst.NULL_DATA.getResultCode(), CodeConst.NULL_DATA.getMessage());
        }
        return new Result<>(changeLogModel);
    }

    /**
     * findByName
     *
     * @return result
     */
    @Override
    @RequestMapping(value = "findByName/{name}", method = RequestMethod.GET)
    @ApiOperation(value = "通过名字查找", notes = "通过名字查找", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "更新日志内容", required = true, dataType = "String", paramType = "path")
    })
    public Result findByName(@PathVariable("name") String name) {
        ChangeLogModel model = service.findByName(name);
        if (model == null) {
            return new Result(CodeConst.NULL_DATA.getResultCode(), CodeConst.NULL_DATA.getMessage());
        }
        return new Result<>(model);
    }

    /**
     * 根据名字删除模型
     *
     * @param name name
     * @return result
     */
    @Override
    public Result<Boolean> delByName(@PathVariable String name) {
        return null;
    }

    /**
     * 根据id删除模型
     *
     * @param id id
     * @return result
     */
    @Override
    public Result<Boolean> delById(@PathVariable Long id) {
        return null;
    }

    /**
     * 添加模型
     *
     * @param model model
     * @return result
     */
    @Override
    public Result<Boolean> add(@RequestBody Object model) {
        return null;
    }

    /**
     * 更新
     *
     * @param model model
     * @return result
     */
    @Override
    public Result<Boolean> update(@RequestBody Object model) {
        return null;
    }

    /**
     * 批量删除
     *
     * @param ids ids
     * @return result
     */
    @Override
    public Result<Boolean> delByIds(@PathVariable List ids) {
        return null;
    }

    /**
     * 分页查询更新日志
     *
     * @return 分页
     */
    @Override
    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    @ApiOperation(value = "分页查询更新日志", notes = "分页查询更新日志", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result findAll() {
        List<ChangeLogModel> pages = service.findAll();
        if (pages == null || pages.size() <= 0) {
            return new Result(CodeConst.NULL_DATA.getResultCode(), CodeConst.NULL_DATA.getMessage());
        }
        return new Result<>(pages);
    }

    /**
     * 带分页
     *
     * @param start    起始页
     * @param pageSize 页码数
     * @return result
     */
    @Override
    public Result<Page> findAll(@PathVariable int start, @PathVariable int pageSize) {
        return null;
    }

    /**
     * 增加更新日志
     *
     * @return result
     */
    @ApiOperation(value = "增加更新日志", notes = "增加更新日志", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Result add(@RequestBody ChangeLogModel model) {
        ChangeLogModel changeLogModel = service.findByName(model.getName());
        if (changeLogModel != null) {
            return new Result(CodeConst.REPEAT.getResultCode(), CodeConst.REPEAT.getMessage());
        }
        changeLogModel = new ChangeLogModel();
        changeLogModel.setName(model.getName());
        changeLogModel.setOnlineTime(model.getOnlineTime());
        ChangeLogModel addModel = service.add(changeLogModel);
        return new Result<>(addModel);
    }


    /**
     * 修改更新日志
     *
     * @return result
     */
    @ApiOperation(value = "修改更新日志", notes = "修改更新日志", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Result update(@RequestBody ChangeLogModel model) {
        ChangeLogModel changeLogModel = service.findByName(model.getName());
        if (changeLogModel == null) {
            return new Result(CodeConst.NULL_DATA.getResultCode(), CodeConst.NULL_DATA.getMessage());
        }
        changeLogModel.setName(model.getName());
        changeLogModel.setOnlineTime(model.getOnlineTime());
        ChangeLogModel updateModel = service.update(changeLogModel);
        return new Result<>(updateModel);
    }


    /**
     * 删除更新日志
     */
    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "删除更新日志", notes = "删除更新日志", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一id", required = true, dataType = "Long", paramType = "path")
    })
    public Result deleteById(@PathVariable("id") Long id) {
        ChangeLogModel changeLogModel = service.findById(id);
        if (changeLogModel == null) {
            return new Result(CodeConst.NULL_DATA.getResultCode(), CodeConst.NULL_DATA.getMessage());
        }
        ChangeLogModel delModel = service.delete(id);
        return new Result<>(delModel);
    }


}

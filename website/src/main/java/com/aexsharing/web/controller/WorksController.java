package com.aexsharing.web.controller;

import com.aexsharing.web.model.WorksModel;
import com.aexsharing.core.base.BaseController;
import com.aexsharing.core.base.Result;
import com.aexsharing.core.constant.CodeConst;
import com.aexsharing.web.service.WorksService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : Kevin Xu
 * github: https://github.com/kevinxu1007
 * email: kevinxu1007@gmail.com
 * Date: 2018/7/16 15:38
 **/

@RequestMapping("/works")
@RestController
@Api(value = "作品相关api", description = "作品相关api")
public class WorksController extends BaseController {

    private final WorksService service;

    @Autowired
    public WorksController(WorksService service) {
        this.service = service;
    }


    @Override
    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "根据id查找作品", notes = "根据id查找作品", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一id", required = true, dataType = "Long", paramType = "path"),
    })
    public Result findById(@PathVariable Long id) {
        WorksModel model = service.findById(id);
        if (model == null) {
            return new Result(CodeConst.NULL_DATA.getResultCode(), CodeConst.NULL_DATA.getMessage());
        }
        return new Result<>(model);
    }

    @Override
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ApiOperation(value = "查找所有", notes = "查找所有", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result findAll() {
        List<WorksModel> all = service.findAll();
        if (all == null || all.isEmpty()) {
            return new Result(CodeConst.NULL_DATA.getResultCode(), CodeConst.NULL_DATA.getMessage());
        }
        return new Result<>(all);
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


    @Override
    @RequestMapping(value = "/findByName/{name}", method = RequestMethod.GET)
    @ApiOperation(value = "根据名字查找作品", notes = "根据名字查找作品", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "作品名字", required = true, dataType = "String", paramType = "path"),
    })
    public Result findByName(@PathVariable String name) {
        WorksModel model = service.findByName(name);
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

    @ApiOperation(value = "添加作品", notes = "添加作品", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result add(@RequestBody WorksModel model) {
        WorksModel addModel = service.findByName(model.getName());
        if (addModel != null) {
            return new Result(CodeConst.REPEAT.getResultCode(), CodeConst.REPEAT.getMessage());
        }
        addModel = service.add(model);
        return new Result<>(addModel);
    }

    @ApiOperation(value = "更新作品", notes = "更新作品", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result update(@RequestBody WorksModel model) {
        WorksModel worksModel = service.findById(model.getId());
        if (worksModel == null) {
            return new Result(CodeConst.CodeOR.getResultCode(), CodeConst.CodeOR.getMessage());
        }
        worksModel = service.update(worksModel);
        return new Result<>(worksModel);
    }


    @ApiOperation(value = "根据id删除作品", notes = "根据id删除作品", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一id", required = true, dataType = "Long", paramType = "path"),
    })
    public Result delete(@PathVariable Long id) {
        WorksModel model = service.findById(id);
        if (model == null) {
            return new Result(CodeConst.NULL_DATA.getResultCode(), CodeConst.NULL_DATA.getMessage());
        }
        service.del(id);
        return new Result<>(model);
    }

}

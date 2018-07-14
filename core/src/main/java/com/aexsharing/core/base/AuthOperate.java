package com.aexsharing.core.base;

import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
/**
 * @author : Kevin Xu
 * Date: 2018/6/8
 */

public interface AuthOperate {


    /**
     * 登录
     *
     * @param name     用户名(邮箱或者电话)
     * @param password 密码
     * @return 结果
     */
    Result<Boolean> login(@PathVariable String name, @PathVariable String password);


    /**
     * 修改密码
     *
     * @param name     用户名(邮箱或者电话)
     * @param password 密码
     * @return 结果
     */
    Result<Boolean> changePassword(@PathVariable String name, @PathVariable String password);

    /**
     * 注册
     *
     * @param name     用户名(邮箱或者电话)
     * @param password 密码
     * @return 是否己发送验证码
     */
    Result<Boolean> register(@PathVariable String name, @PathVariable String password);

    /**
     * 验证
     *
     * @param validCode 验证码
     * @param time      发送时间
     * @return 是否验证通过
     */
    Result<Boolean> validate(@PathVariable int validCode, @PathVariable Date time);

}

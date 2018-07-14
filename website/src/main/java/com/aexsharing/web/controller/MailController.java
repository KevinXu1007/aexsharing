package com.aexsharing.web.controller;

import com.aexsharing.web.util.MailUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Kevin Xu
 * github: https://github.com/kevinxu1007
 * email: kevinxu1007@gmail.com
 * Date: 2018/7/16 15:38
 **/

@RestController
@RequestMapping("/mail")
public class MailController {

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public boolean sendMail() {
        return MailUtil.send("hupengbest@163.com", "测试邮件是否正常", "测试");
    }

}

package com.aexsharing.web.view;

/**
 * @author : Kevin Xu
 * github: https://github.com/kevinxu1007
 * email: kevinxu1007@gmail.com
 * Date: 2018/7/16 15:38
 **/

public enum UserView {
    /**
     * login
     */
    LOGIN("login"),
    REGISTER("register"),
    REGISTER_INFO("info"),
    INDEX("index"),
    API("swagger-ui.html");

    private String name;

    UserView(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

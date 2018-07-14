package com.aexsharing.core.untils;

import javax.servlet.http.HttpSession;

/**
 * @author : Kevin Xu
 * Date: 2018/6/8
 */

public class AuthUtil {
    public static boolean isAuthorized(HttpSession session) {
        String currentUser = (String) session.getAttribute("currentUser");
        return !(currentUser == null || currentUser.isEmpty());
    }

}

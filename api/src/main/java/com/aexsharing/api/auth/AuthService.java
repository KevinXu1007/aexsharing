package com.aexsharing.api.auth;

import com.aexsharing.api.user.User;

public interface AuthService {
    User register(User userToAdd);
    String login(String username, String password);
    String refresh(String oldToken);
}

package com.jakub.taskmanagementapi.services;

import com.jakub.taskmanagementapi.dto.request.LoginReq;
import com.jakub.taskmanagementapi.models.User;

public interface AuthenticationService {
    String login(LoginReq loginReq);

    void register(User user, Boolean isAdmin);
}

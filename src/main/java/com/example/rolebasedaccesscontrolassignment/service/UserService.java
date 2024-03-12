package com.example.rolebasedaccesscontrolassignment.service;

import com.example.rolebasedaccesscontrolassignment.dto.ResponseDto.UserResponse;

public interface UserService {
        UserResponse getUser(String userName, String password);

        String DeleteUser(String userName, String password);

        String updateNumber(String userName, String password, String number);
    }


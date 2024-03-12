package com.example.rolebasedaccesscontrolassignment.transformer;

import com.example.rolebasedaccesscontrolassignment.dto.RequestDto.UserRequest;
import com.example.rolebasedaccesscontrolassignment.dto.ResponseDto.UserResponse;
import com.example.rolebasedaccesscontrolassignment.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserTransformer {

    static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static User UserRequestToUser (UserRequest userRequest){
        return User.builder()
                .userName(userRequest.getUserName())
                .phoneNumber(userRequest.getPhoneNo())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .build();
    }
    public static UserResponse UserToUserResponse(User user){
        return UserResponse.builder()
                .userName(user.getUserName())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }
}

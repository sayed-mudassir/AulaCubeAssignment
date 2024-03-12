package com.example.rolebasedaccesscontrolassignment.controller;

import com.example.rolebasedaccesscontrolassignment.dto.ResponseDto.UserResponse;
import com.example.rolebasedaccesscontrolassignment.model.User;
import com.example.rolebasedaccesscontrolassignment.repository.UserRepository;
import com.example.rolebasedaccesscontrolassignment.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    UserRepository userRepository;

    //    Add admin
    @PostMapping("/addAdmin/username/{username}/password/{password}/phoneNumber/{phoneNumber}")
    public UserResponse addAdmin(@PathVariable("username") String username,
                                 @PathVariable("password") String password,
                                 @PathVariable("phoneNumber") String phoneNumber){

        User user = new User();
        user.setUserName(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setPhoneNumber(phoneNumber);
        user.setRole("ROLE_ADMIN");

        User savedUser = userRepository.save(user);
        return UserTransformer.UserToUserResponse(savedUser);
    }
    //    add user
    @PostMapping("/addUser/username/{username}/password/{password}/phoneNumber/{phoneNumber}")
    public UserResponse addUser(@PathVariable("username") String username,
                        @PathVariable("password") String password,
                        @PathVariable("phoneNumber") String phoneNumber){

        User user = new User();
        user.setUserName(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setPhoneNumber(phoneNumber);
        user.setRole("ROLE_USER,ROLE_RANDOM");

        User savedUser = userRepository.save(user);
        return UserTransformer.UserToUserResponse(user);
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to PUBLIC area!!!";
    }
}

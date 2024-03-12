package com.example.rolebasedaccesscontrolassignment.service.impl;

import com.example.rolebasedaccesscontrolassignment.config.securityConfig;
import com.example.rolebasedaccesscontrolassignment.dto.ResponseDto.UserResponse;
import com.example.rolebasedaccesscontrolassignment.exceptions.IncorrectPasswordException;
import com.example.rolebasedaccesscontrolassignment.exceptions.UserNotFoundException;
import com.example.rolebasedaccesscontrolassignment.model.User;
import com.example.rolebasedaccesscontrolassignment.repository.UserRepository;
import com.example.rolebasedaccesscontrolassignment.service.UserService;
import com.example.rolebasedaccesscontrolassignment.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
     UserRepository userRepository;

    @Override
    public UserResponse getUser(String userName, String password) {
        Optional<User> userOptional = userRepository.findByUserName(userName); // find user with email
        if(userOptional.isEmpty()){ //check if user is present or not
            throw new UserNotFoundException("Invalid User"); //if no user found
        }
        User user = userOptional.get();
        if (!password.equals(user.getPassword())){ //check password
            throw new IncorrectPasswordException("Wrong password"); //if password is wrong
        }
        return UserTransformer.UserToUserResponse(user);
    }

    @Override
    public String DeleteUser(String userName, String password) {
        User user = userRepository.findByUserName(userName).get();
        if(user != null){
            if(securityConfig.matches(password, user.getPassword())){
                userRepository.delete(user);
                return "User deleted successfully";
            }
            else
                throw new IncorrectPasswordException("Inncorrect Password");
        }
        throw new UserNotFoundException("Email Id Not Found");
    }

    @Override
    public String updateNumber(String userName, String password, String number) {
        Optional<User> userOptional = userRepository.findByUserName(userName); // find user with email
        if(userOptional.isEmpty()){ //check if user is present or not
            throw new UserNotFoundException("Invalid User"); //if no user found
        }
        User user = userOptional.get();
        if (!password.equals(user.getPassword())){ //check password
            throw new IncorrectPasswordException("Wrong password"); //if password is wrong
        }
        user.setPhoneNumber(number);
        userRepository.save(user);
        return "Phone number updated successfully";
    }
}

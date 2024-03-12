package com.example.rolebasedaccesscontrolassignment.controller;

import com.example.rolebasedaccesscontrolassignment.dto.ResponseDto.UserResponse;
import com.example.rolebasedaccesscontrolassignment.model.User;
import com.example.rolebasedaccesscontrolassignment.repository.UserRepository;
import com.example.rolebasedaccesscontrolassignment.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to ADMIN area!!!";
    }

    @Autowired
    UserRepository userRepository;

    @GetMapping("/getUser/username/{username}")
    public UserResponse getUserByUsername (@PathVariable("username") String username){
        User user = userRepository.findByUserName(username).get();
        if (user == null){
            throw new UsernameNotFoundException("Invalid username");
        }
        return UserTransformer.UserToUserResponse(user);
    }
    @DeleteMapping("/deleteUser/username/{username}/phoneNumber/{phoneNumber}")
    public ResponseEntity deleteUserByUsername(@PathVariable("username") String username, @PathVariable("phoneNumber") String phoneNumber){
        User user = userRepository.findByUserName(username).get();
        if (user == null){
            throw new UsernameNotFoundException("Invalid username");
        }
        if (user.getPhoneNumber() != phoneNumber) {
            throw new UsernameNotFoundException("Invalid phone number");
        }
        userRepository.deleteById(user.getId());
        return new ResponseEntity("user has been deleted !!!!!!!!!!!!", HttpStatus.ACCEPTED);
    }
    @PutMapping("/updatePhoneNumber/username/{username}/oldPhoneNumber{oldPhoneNumber}/newPhoneNUmber/{newPhoneNumber}")
    public ResponseEntity updatePhoneNumber (@PathVariable("username") String username,
                                             @PathVariable("oldPhoneNumber") String oldPhoneNumber,
                                             @PathVariable("newPhoneNumber") String newPhoneNumber){
        User user = userRepository.findByUserName(username).get();
        if (user == null){
            throw new UsernameNotFoundException("Invalid username");
        }
        if (user.getPhoneNumber() != oldPhoneNumber){
            throw new UsernameNotFoundException("Invalid previous phone number");
        }
        user.setPhoneNumber(newPhoneNumber);
        userRepository.save(user);
        return new ResponseEntity("phone number updated",HttpStatus.ACCEPTED);
    }

}

package com.example.rolebasedaccesscontrolassignment.controller;

import com.example.rolebasedaccesscontrolassignment.dto.RequestDto.UserRequest;
import com.example.rolebasedaccesscontrolassignment.dto.ResponseDto.UserResponse;
import com.example.rolebasedaccesscontrolassignment.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;
    @GetMapping("/get")
    public ResponseEntity getUser(@RequestParam("user-name")String userName,@RequestParam("password")String password ){
        try {
            UserResponse response = userService.getUser(userName,password);
            return new ResponseEntity(response,HttpStatus.FOUND);
        }
       catch (Exception e){
           return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
       }
    }
    @DeleteMapping("/delete/{emailId}/{password}")
    public ResponseEntity DeleteUser(@PathVariable String userName, @PathVariable String password){
        try{
            String response = userService.DeleteUser(userName, password);
        return new ResponseEntity(response,HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.ACCEPTED);
        }
    }

    @PutMapping("/update-number/{emailId}/{password}/{number}")
    public ResponseEntity updateNumber (@PathVariable String email, @PathVariable String password, @PathVariable String number){
        try {
            String response = userService.updateNumber(email, password, number);
            return new ResponseEntity(response, HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.ACCEPTED);
        }
    }
}

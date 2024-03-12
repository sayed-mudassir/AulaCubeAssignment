package com.example.rolebasedaccesscontrolassignment.config;

import com.example.rolebasedaccesscontrolassignment.config.UserDetailsCreator;
import com.example.rolebasedaccesscontrolassignment.model.User;
import com.example.rolebasedaccesscontrolassignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUserName(username).get();
        if(user ==null){
            throw new UsernameNotFoundException("Invalid username");
        }

        return new UserDetailsCreator(user);
    }
}
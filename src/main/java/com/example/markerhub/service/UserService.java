package com.example.markerhub.service;

import com.example.markerhub.entity.User;
import com.example.markerhub.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {
    @Autowired
    UserMapper userMapper;

    public User getUserById(long id) {
        User user = userMapper.findUserById(id);
        if (user == null) {
            throw new RuntimeException("User not found by id.");
        }
        return user;
    }
    public User getUserByUsername(String  username) {
        User user = userMapper.findUserByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found by username.");
        }
        return user;
    }


    public User registerUser(String username, String password) {
        User user = userMapper.findUserByUsername(username);
        if (user != null) {

        }
        user.setUserName(username);
        user.setPassword(password);
        userMapper.insert(user);
        return user;
    }

    public User loginUser(String username, String password) {
        return userMapper.login(username, password);
    }
}
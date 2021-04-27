package com.example.markerhub.controller;

import com.example.markerhub.common.Result;
import com.example.markerhub.entity.User;
import com.example.markerhub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;
   // @RequiresAuthentication
    @GetMapping("/{id}")
    public Result test(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        return Result.succ(user);
    }
    @PostMapping("/user")
    public Result testUser(@Validated @RequestBody User user) {
        return Result.succ(user);
    }
    @PostMapping("/register")
    public Result registerUser(@RequestParam("username") String username, @RequestParam("password") String password) {
        User user=  userService.registerUser(username, password);
        return Result.succ(user);
    }

    @PostMapping("/login11")
    public Result loginUser(@RequestParam("username") String username, @RequestParam("password") String password) {
        User user= userService.loginUser(username, password);
        if(user!=null){
            return  Result.succ(user);
        }else {
            return Result.fail("登录失败");
        }
    }


}

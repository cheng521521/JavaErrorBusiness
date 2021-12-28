package com.cpx.controller;

import com.cpx.entity.UserEntity;
import com.cpx.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author cpx
 * @Description
 * @date 2021/12/22
 */
@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("wrong1")
    public int wrong1(@RequestParam("name") String name) {
        userService.createUserWrong1(name);
        return userService.getUserCount(name);
    }


    @GetMapping("wrong2")
    public int wrong2(@RequestParam("name") String name){
        try {
            userService.createWrong2(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userService.getUserCount(name);
    }

    @GetMapping("wrong")
    public int wrong(@RequestParam("name") String name) {
        try {
            userService.createUserWrong(new UserEntity(name));
        } catch (Exception ex) {
            log.error("createUserWrong failed, reason:{}", ex.getMessage());
        }
        return userService.getUserCount(name);
    }
}

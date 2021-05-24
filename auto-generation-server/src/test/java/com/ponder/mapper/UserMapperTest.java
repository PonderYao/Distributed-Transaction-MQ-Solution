package com.ponder.mapper;

import com.ponder.AutoGenerationServerApplication;
import com.ponder.entity.User;
import com.ponder.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = AutoGenerationServerApplication.class)
class UserMapperTest {

    @Resource
    UserService userService;

    @Test
    public void test1(){
        List<User> userList = userService.list();
        for (User user : userList) {
            System.out.println(user);
        }

    }



}
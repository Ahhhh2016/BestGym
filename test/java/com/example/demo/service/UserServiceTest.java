package com.example.demo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.DemoApplicationTest;
import com.example.demo.domain.Employee;
import com.example.demo.domain.User;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.junit.Assert.*;

@Component
public class UserServiceTest extends DemoApplicationTest {
    @Autowired
    private UserService userService;

    @Test
    public void testLogin() {
        User user = userService.getOne(new QueryWrapper<User>().eq("username", "zk"));
        Assert.assertNotNull(user);
    }

    @Test
    public void testAdd() throws JSONException {
        JSONObject object = JSON.parseObject("{\"username\":\"test\",\"email\":\"test@test\",\"password\":12345}");
        User user = new User();
        user.setUsername(object.getString("username"));
        user.setEmail(object.getString("email"));
        user.setPassword(object.getString("password"));
        User us = userService.getOne(new QueryWrapper<User>().eq("username", object.getString("username")));
        Assert.assertNull(us);
        boolean u=userService.save(user);
        Assert.assertTrue(u);
    }

    @Test
    public void testModify() {
        JSONObject object = JSON.parseObject("{\"username\":\"Tom\",\"password\":12345}");
        User user1  = userService.getOne(new QueryWrapper<User>().eq("username", object.getString("username")));

        UpdateWrapper<User> updateWrapper = new UpdateWrapper<User>();
        updateWrapper.eq("username", object.getString("username"));
        updateWrapper.set("password", object.getString("password"));

        boolean u = userService.update(user1, updateWrapper);

        Assert.assertEquals("12345", user1.getPassword());
        Assert.assertTrue(u);

    }
}
package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.DemoApplicationTest;
import com.example.demo.domain.Employee;
import com.example.demo.domain.Facility;
import com.example.demo.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.junit.Assert.*;

@Component
public class EmployeeServiceTest extends DemoApplicationTest {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private UserService userService;
    @Autowired
    private FacilityService facilityService;

    @Test
    public void TestShowAll() {
        List<Employee> e_list = employeeService.list();

        Assert.assertNotNull(e_list);
    }

    @Test
    public void TestFind() {
        List<User> userList = userService.list(new QueryWrapper<User>().eq("username", "zk"));
        List<User> userList1 = userService.list(new QueryWrapper<User>().eq("username", "xxxxx"));

        Assert.assertNotNull(userList);

    }

    @Test
    public void TestListALL() {
        List<User> userList = userService.list();

        Assert.assertNotNull(userList);
    }

    @Test
    public void TestShowFacility() {
        List<Facility> f_List = facilityService.list();

        Assert.assertNotNull(f_List);
    }

    @Test
    public void TestDeleteUser() {
        User userDelete = new User();
        userDelete.setUsername("test");
        userDelete.setPassword("test");
        boolean flag = userService.save(userDelete);
        QueryWrapper<User> userdelete = new QueryWrapper<User>().eq("username", userDelete.getUsername());
        int userId = userDelete.getId();
        QueryWrapper<User> before= new QueryWrapper<User>().eq("id", userId);
        Assert.assertNotNull(before);
        boolean u=userService.remove(before);
        QueryWrapper<User> after = new QueryWrapper<User>().eq("id", userId);
        Assert.assertTrue(u);
    }


}
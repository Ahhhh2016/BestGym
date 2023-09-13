package com.example.demo.Controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.domain.Activity;
import com.example.demo.domain.Facility;
import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private UserService userService;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception{
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void showAllEmployee() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/employee/showAllEmployee")
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertTrue("True", status==200);

        System.out.println("输出 " + mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void findUser() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/employee/findUser")
                .param("username", "zk")
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertTrue("True", status==200);

        System.out.println("输出 " + mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void listUser() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/employee/listUser")
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertTrue("True", status==200);

        System.out.println("输出 " + mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void showFacility() throws Exception {

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/employee/showFacility")
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertTrue("True", status==200);

        System.out.println("输出 " + mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void deleteUser() throws Exception {
        User usertest = new User();
        usertest.setUsername("test1");
        usertest.setPassword("111111");
        boolean flag = userService.save(usertest);

        User usertestx= userService.getOne(new QueryWrapper<User>().eq("username", usertest.getUsername()));
        int id = usertestx.getId();
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/employee/deleteUser")
                .param("userId", "21" )
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertTrue("True", status==200);

        System.out.println("输出 " + mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void showActivity() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/employee/showActivity")
                        .param("type", "swimming pool")
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertTrue("True", status==200);

        System.out.println("输出 " + mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void modifyFacility() throws Exception {
//        Facility facility = new Facility();
//        facility.setFacilityType("swimming pool");
//        facility.setMaxCapacity(50);
//        facility.setNumFacility(1);
//        String jsonStr = JSON.toJSONString(facility);
//
//        MvcResult mvcResult = mockMvc.perform(
//                MockMvcRequestBuilders.post("/employee/modifyFacility")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonStr)
//                        .accept(MediaType.APPLICATION_JSON)
//
//        ).andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print()).andReturn();
//
//        int status = mvcResult.getResponse().getStatus();
//        assertTrue("True", status==200);
//
//        System.out.println("输出 " + mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void modifyActivity() throws Exception {
//        Activity activityTest = new Activity();
//        activityTest.setActivityName("general use");
//        activityTest.setActivityToWhichFacility(1);
//        activityTest.setCapacity(2);
//        activityTest.setPrice(40.00F);
//        String jsonStr = JSON.toJSONString(activityTest);
//
//        MvcResult mvcResult = mockMvc.perform(
//                MockMvcRequestBuilders.get("/employee/ModifyActivity")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonStr)
//                        .accept(MediaType.APPLICATION_JSON)
//
//        ).andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print()).andReturn();
//
//        int status = mvcResult.getResponse().getStatus();
//        assertTrue("True", status==200);
//
//        System.out.println("输出 " + mvcResult.getResponse().getContentAsString());
    }
}
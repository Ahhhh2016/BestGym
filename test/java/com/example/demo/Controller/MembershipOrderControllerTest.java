package com.example.demo.Controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.domain.MembershipOrder;
import com.example.demo.domain.User;
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

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MembershipOrderControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception{
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void TestEnter() throws Exception {
        User usertest = new User();
        usertest.setUsername("zk");
        usertest.setPassword("12344");
        usertest.setMembership(0);

        String jsonStr = JSON.toJSONString(usertest);

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/membershipOrder/enter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr)
                        .accept(MediaType.APPLICATION_JSON)
                        .param("type", "1")

        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertTrue("True", status==200);

        System.out.println("输出 " + mvcResult.getResponse().getContentAsString());

    }

    @Test
    public void testCancel() {
    }
}
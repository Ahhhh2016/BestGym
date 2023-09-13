package com.example.demo.Controller;

import com.alibaba.fastjson.JSON;
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

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ActivityOrderControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception{
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void allOrder() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/activityOrder/allOrder")

        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertTrue("True", status==200);

        System.out.println("输出 " + mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void viewOrder() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/activityOrder/viewOrder")
                .param("userId", "1")

        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertTrue("True", status==200);

        System.out.println("输出 " + mvcResult.getResponse().getContentAsString());

    }

    @Test
    public void findByStatus() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/activityOrder/findByStatus")
                        .param("paymentStatus", "0")
                        .param("usageStatus", "0")

        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertTrue("True", status==200);

        System.out.println("输出 " + mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void findByUserStatus() throws Exception {
        User user = new User();
        user.setUsername("zk");
        user.setPassword("1233");
        String jsonStr = JSON.toJSONString(user);

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/activityOrder/findByUserStatus")
                        .param("userid", "1")
                        .param("paymentStatus", "0")
                        .param("usageStatus", "0")

        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertTrue("True", status==200);

        System.out.println("输出 " + mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void modifyPaymentStatus() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/activityOrder/modifyPaymentStatus")
                        .param("orderid", "1")


        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertTrue("True", status==200);

        System.out.println("输出 " + mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void modifyUsageStatus() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/activityOrder/modifyUsageStatus")
                        .param("orderid", "4")


        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertTrue("True", status==200);

        System.out.println("输出 " + mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void setUpOrder() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/activityOrder/setUpOrder")
                        .param("userid", "2")
                        .param("facilityId", "1")
                        .param("number", "3")
                        .param("activityid", "2")
                        .param("timePeriod", "20:00-22:00")
                        .param("date","2022-5-20")


        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();

//        int status = mvcResult.getResponse().getStatus();
//        assertTrue("True", status==200);
//
//        System.out.println("输出 " + mvcResult.getResponse().getContentAsString());
    }
}
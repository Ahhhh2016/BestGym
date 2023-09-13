package com.example.demo.Controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.domain.Facility;
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
import springfox.documentation.spring.web.json.Json;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FacilityControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception{
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void findAllFacility() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/facility/findAllFacility")
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertTrue("True", status==200);

        System.out.println("输出 " + mvcResult.getResponse().getContentAsString());

    }

    @Test
    public void detail() throws Exception {
        Facility facility = new Facility();
        facility.setFacilityType("swimming pool");
        facility.setMaxCapacity(40);
        facility.setNumFacility(1);
        String jsonStr = JSON.toJSONString(facility);
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/facility/detail")
                        .param("type", "swimming pool"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String json = mvcResult.getResponse().getContentAsString();
        Assert.assertTrue("True", status==200);

        System.out.println("输出 " + mvcResult.getResponse().getContentAsString());
    }
}
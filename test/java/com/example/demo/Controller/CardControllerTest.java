package com.example.demo.Controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.domain.Card;
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
public class CardControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception{
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void showAllCard() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/card/showAllCards")
                        .param("id", "1")

        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertTrue("True", status==200);

        System.out.println("输出 " + mvcResult.getResponse().getContentAsString());

    }

    @Test
    public void addCard() throws Exception {
        Card test = new Card();
        test.setBankname("中国人民银行");
        test.setNumber("xxx12344ss");
        test.setUserid(1);

        String jsonStr = JSON.toJSONString(test);

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/card/addCard")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr)
                        .accept(MediaType.APPLICATION_JSON)

        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertTrue("True", status==200);

        System.out.println("输出 " + mvcResult.getResponse().getContentAsString());

    }

    @Test
    public void deleteCard() {
    }
}
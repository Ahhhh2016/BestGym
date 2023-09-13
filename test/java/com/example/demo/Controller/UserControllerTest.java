package com.example.demo.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.controller.UserController;
import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    /**
     * 1、mockMvc.perform执行一个请求。
     * 2、MockMvcRequestBuilders.get("XXX")构造一个请求。
     * 3、ResultActions.param添加请求传值
     * 4、ResultActions.accept(MediaType.TEXT_HTML_VALUE))设置返回类型
     * 5、ResultActions.andExpect添加执行完成后的断言。
     * 6、ResultActions.andDo添加一个结果处理器，表示要对结果做点什么事情
     *   比如此处使用MockMvcResultHandlers.print()输出整个响应结果信息。
     * 7、ResultActions.andReturn表示执行完成后返回相应的结果。
     */

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private UserController userController;
    @Autowired
    private UserService userService;

    private MockMvc mockMvc;
    private MockHttpSession session;

    @Before
    public void setUp() throws Exception{
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }


    @Test
    public void info() throws Exception {
        JSONObject object = JSON.parseObject("{\"username\":\"zk\",\"password\":123}");
        String json = "{\"username\":\"zk\",\"password\":123}";
        mockMvc.perform(MockMvcRequestBuilders
                .get("/user/info")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .param("name", "zk")
        ).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Hello zk!"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void test() throws Exception {
        User user = new User();
        user.setUsername("zk");
        user.setPassword("123");
        String jsonStr = JSON.toJSONString(user);
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/user/test")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonStr)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertTrue("True", status==200);

        System.out.println("输出 " + mvcResult.getResponse().getContentAsString());
//        RequestBuilder request = MockMvcRequestBuilders.post("/user/test")
//                .accept(MediaType.APPLICATION_JSON_UTF8)
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .content(jsonStr);
//
//        MvcResult mvcResult = mockMvc.perform(request).andReturn();
//        int status = mvcResult.getResponse().getStatus();
//
//        System.out.println("返回状态码：" + status);
//         String user = "{\"userNo\":\"1000\",\"username\":\"Tom\",\"age\":18,\"createDate\":\"2019-12-31T02:51:52" +
//                ".326+0000\"}";
//        String jsonStr = "{\"username\":\"zk\",\"password\":123\"}";
//        mockMvc.perform(
//                MockMvcRequestBuilders
//                        .get("/user/test")
//                        .content(jsonStr)
//                        .contentType(MediaType.APPLICATION_JSON)
//        ).andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string("success"))
//                .andDo(MockMvcResultHandlers.print());
    }



    @Test
    public void login() throws Exception {
//        JSONObject object = JSON.parseObject("{\"username\":\"test\",\"password\":test}");
//
//        mockMvc.perform(MockMvcRequestBuilders
//                .request(HttpMethod.POST, "/user/login")
//                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
//                .content(object.toString())
//                .session(session)
//        ).andExpect(MockMvcResultMatchers.status().isOk())
//         .andDo(MockMvcResultHandlers.print());
        User user = new User();
        user.setUsername("zk");
        user.setPassword("123");
        String jsonStr = JSON.toJSONString(user);
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertTrue("True", status==200);

        System.out.println("输出 " + mvcResult.getResponse().getContentAsString());

    }

    @Test
    public void logout() {
    }

    @Test
    public void regist() throws Exception {
        User user = new User();
        user.setUsername("xtr");
        user.setPassword("1233");
        String jsonStr = JSON.toJSONString(user);
        MvcResult mvcResult1 = mockMvc.perform(
                MockMvcRequestBuilders.post("/user/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();

        int status = mvcResult1.getResponse().getStatus();
        Assert.assertTrue("True", status==200);
        System.out.println("输出 " + mvcResult1.getResponse().getContentAsString());

        User user1 = new User();
        user1.setUsername("zk");
        user1.setPassword("123");
        String jsonStr1 = JSON.toJSONString(user1);
        MvcResult mvcResult2 = mockMvc.perform(
                MockMvcRequestBuilders.post("/user/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr1)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();

        int status1 = mvcResult1.getResponse().getStatus();
        Assert.assertTrue("True", status1==200);
        System.out.println("输出 " + mvcResult2.getResponse().getContentAsString());

    }

    @Test
    public void modify() throws Exception {
        User user = new User();
        user.setUsername("zk");
        user.setPassword("1233");
        String jsonStr = JSON.toJSONString(user);
        MvcResult mvcResult1 = mockMvc.perform(
                MockMvcRequestBuilders.post("/user/modify")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();

        int status = mvcResult1.getResponse().getStatus();
        Assert.assertTrue("True", status==200);
        System.out.println("输出 " + mvcResult1.getResponse().getContentAsString());
    }
}
package com.cxy.security.cxysecuritydemo.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

/***
 * Created by Caoxingyun on 2019/03/11
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void whenQuerySuccess() throws Exception {
        String string = mockMvc.perform(MockMvcRequestBuilders.get("/user")
                .param("userName", "cxy")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
//                json $代表查询的返回的根
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
        .andReturn().getResponse().getContentAsString();
        System.out.println(string);
    }

    @Test
    public void whenGetInfoSuccess() throws Exception {
        String string = mockMvc.perform(MockMvcRequestBuilders.get("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
//                json $代表查询的返回的根
                .andExpect(MockMvcResultMatchers.jsonPath("$.userName").value("cxy"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(string);
    }

    @Test
    public void whenCreateUserSuccess() throws Exception {
        String content = "{\"userName\":\"cxy\",\"age\":\"22\",\"birthday\":" + (new Date()).getTime() + "}";
          String string = mockMvc.perform(MockMvcRequestBuilders.post("/user")
                  .content(content)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
//                json $代表查询的返回的根
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("111"))
                  .andReturn().getResponse().getContentAsString();
        System.out.println(string);
    }

    @Test
    public void whenUpdateUserSuccess() throws Exception {
//        Date birthDay = new Date(LocalDateTime.now().plusYears(2).atZone(ZoneId.systemDefault()).toInstant().toString());
        Date birthDay = new Date();
        String content = "{\"id\":\"1\",\"userName\":\"cxy\",\"passWord\":\"cxy\",\"age\":22,\"birthDay\":" + birthDay.getTime() + "}";
        String string = mockMvc.perform(MockMvcRequestBuilders.put("/user/1")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
//                json $代表查询的返回的根
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(string);
    }

    @Test
    public void whenDeleteUserSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}

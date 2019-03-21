package com.cxy.security.cxysecuritydemo.controller;

import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/***
 * Created by Caoxingyun on 2019/03/18
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class FileControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void upload() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/file").file(new MockMultipartFile("file","test.txt","multipart/form-data","Helloween".getBytes())))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void download() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/file/1552903963015")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
}

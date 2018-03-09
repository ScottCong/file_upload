package com.demo.springdemo.controller;

import com.demo.springdemo.DemoApplication;
import com.demo.springdemo.dao.MetaData;
import com.demo.springdemo.service.MetaService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = DemoApplication.class)
public class FileManageControllerTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;

    @Mock
    private MetaService metaService;

    @InjectMocks
    private FileManageController controller;

    @Before
    public void setUp() throws Exception {

        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void saveFile() throws Exception {

        ResultMatcher ok = MockMvcResultMatchers.status().isOk();

        MockMultipartFile file = new MockMultipartFile("file", "hello.txt", MediaType.TEXT_PLAIN_VALUE, "Hello, World!".getBytes());

        MetaData metaData = new MetaData();

        when(metaService.upLoadFileById(file, metaData)).thenReturn(metaData);

        MockHttpServletRequestBuilder builder =
//                MockMvcRequestBuilders.fileUpload("/Users/cs/Documents/upload/")
//                        .file(file);
                MockMvcRequestBuilders.post("/meta/file");
        builder.param("username", "Scott")
                .param("DateBirth", "whatever");


        mvc.perform(builder).andExpect(ok).andDo(MockMvcResultHandlers.print());

    }
}


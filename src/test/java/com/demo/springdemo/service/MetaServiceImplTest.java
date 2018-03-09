package com.demo.springdemo.service;

import com.demo.springdemo.dao.MetaData;
import com.demo.springdemo.exceptions.IllegalFileNameException;
import com.demo.springdemo.repository.MetaRepo;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class MetaServiceImplTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    private String root;

    MetaData metaData = new MetaData();

    @Mock
    private MetaRepo metaRepoMock;

    @InjectMocks
    private MetaServiceImpl metaService;

    @Before
    public void setUp() throws Exception{
        root = folder.getRoot().getAbsolutePath();
        this.metaData.setId(1L);
        this.metaData.setFileName("hello.txt");
        this.metaData.setUploadDate(new Date());
        this.metaData.setName("Scott");
    }

    @Test
    public void upLoadFileById() throws Exception {

        when(metaRepoMock.save(metaData)).thenReturn(metaData);

        MockMultipartFile file = new MockMultipartFile("file", "hello.txt", MediaType.TEXT_PLAIN_VALUE, "Hello, World!".getBytes());

        assertTrue(metaService.upLoadFileById(file,metaData).getClass().equals(metaData.getClass()));
    }

    @Test(expected = IllegalFileNameException.class)
    public void upLoadFileByIdFileNameException() throws Exception {

        when(metaRepoMock.save(metaData)).thenReturn(metaData);

        MockMultipartFile file = new MockMultipartFile("file", "hello.Scott.txt", MediaType.TEXT_PLAIN_VALUE, "Hello, World!".getBytes());

        metaService.upLoadFileById(file,metaData);
    }
}
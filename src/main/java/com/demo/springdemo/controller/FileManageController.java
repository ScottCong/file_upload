package com.demo.springdemo.controller;

import com.demo.springdemo.dao.MetaData;
import com.demo.springdemo.service.MetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.*;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/meta")
public class FileManageController {

    MetaService metaService;

    @Autowired
    public void SetMetaRepo(MetaService metaService){
        this.metaService = metaService;
    }


    @RequestMapping(path = "/file", method = RequestMethod.POST)
    public MetaData saveFile(Model model,
                             @RequestParam("file")MultipartFile file,
                             @RequestParam("username")String userName,
                             @RequestParam("DateBirth")String dateBirth) throws Exception {

        MetaData data = new MetaData();
        data.setUploadDate(new Date());
        data.setName(userName);
        data.setFileName(file.getOriginalFilename());

        metaService.upLoadFileById(file,data);
        return data;
    }

    @RequestMapping(path = "/file",method = RequestMethod.GET)
    public ResponseEntity getFileMetaDataByName(Model mode,
                                                @RequestParam("fileName") String fileName){

        List<MetaData> metaDatas = metaService.getMetaDataByFileName(fileName);
        if(metaDatas == null || metaDatas.size() == 0){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity(metaDatas, HttpStatus.OK);
        }
    }

    @RequestMapping(path = "/file/{id}", method = RequestMethod.GET)
    public ResponseEntity getFileMetaData(Model model,
                                  @PathVariable("id") Long id){

        MetaData data = metaService.retrive(id);
        ResponseEntity entity;

        if(data == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(data,HttpStatus.OK);
    }


    @RequestMapping(path = "/download/{id}", method = RequestMethod.GET)
    public void getFile(Model mode,
                                  HttpServletResponse response,
                                  @PathVariable("id") Long id) throws IOException {

        Resource resource = metaService.downLoadById(id);

        if(resource == null){
            String errorMessage = "Sorry. The file you are looking for does not exist";
            System.out.println(errorMessage);
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
            outputStream.close();
            return;
        }

        String mimeType= URLConnection.guessContentTypeFromName(resource.getFilename());
        if(mimeType==null){
            System.out.println("mimetype is not detectable, will take default");
            mimeType = "application/octet-stream";
        }

        response.setContentType(mimeType);

        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + resource.getFilename() +"\""));

        response.setContentLength((int) resource.contentLength());

        InputStream stream = resource.getInputStream();

        FileCopyUtils.copy(stream, response.getOutputStream());

        return ;
    }


}

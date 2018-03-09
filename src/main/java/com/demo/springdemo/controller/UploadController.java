package com.demo.springdemo.controller;

import com.demo.springdemo.service.MetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {

    MetaService metaService;

    @Autowired
    public void setMetaService(MetaService metaService) {
        this.metaService = metaService;
    }

//    @PostMapping("/upload")
//    public String upload(@RequestParam("file") MultipartFile file){
//        metaService.upLoad(file);
//        return "test";
//    }
}

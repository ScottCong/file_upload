package com.demo.springdemo.service;

import com.demo.springdemo.dao.MetaData;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

public interface MetaService {


    MetaData retrive(Long Id);

    MetaData upLoadFileById(MultipartFile file, MetaData data) throws Exception;


    Resource downLoadById(Long id);

    List<MetaData> getMetaDataByFileName(String fileName);

    List<MetaData> getMetaDataByTime(Date date);
}

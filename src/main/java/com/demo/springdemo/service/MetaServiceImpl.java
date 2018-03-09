package com.demo.springdemo.service;
import com.demo.springdemo.dao.MetaData;
import com.demo.springdemo.exceptions.IllegalFileNameException;
import com.demo.springdemo.repository.MetaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@PropertySource("classpath:application.properties")
public class MetaServiceImpl implements MetaService{

    @Value("${rootlocation}")
    private String root;

    public void setRoot(String root) {
        this.root = root;
    }

    private MetaRepo metaRepo;

    @Autowired
    public void SetMetaRepo(MetaRepo metaRepo){
        this.metaRepo = metaRepo;
    }


    public MetaServiceImpl(){

    }


    @Override
    public MetaData retrive(Long id) {
        Optional<MetaData> opt = metaRepo.findById(id);
        if(opt.isPresent()){
            return opt.get();
        }
        else{
            return null;
        }
    }


    @Override
    @Transactional
    public MetaData upLoadFileById(MultipartFile file, MetaData metaData)throws Exception {
        if(file.isEmpty()){
            throw new Exception();
        }

        int dotCounter = 0;
        for(char c: file.getOriginalFilename().toCharArray()){
            if(c == '.')dotCounter++;
        }

        if(dotCounter >= 2){
            throw new IllegalFileNameException("file has illegal name");
        }

        MetaData md = metaRepo.save(metaData);
        Long id = md.getId();
        try{
            byte[] bytes = file.getBytes();
            Path path = Paths.get(root + id + "." + file.getOriginalFilename());
            Files.write(path, bytes);
        }
        catch(Exception e){
            throw e;
        }
        return  md;
    }

    @Override
    @Transactional
    public Resource downLoadById(Long id) {

        System.out.println("download method called");
        MetaData metaData = metaRepo.findById(id).get();
        Resource resource = new FileSystemResource(root + id + "." + metaData.getFileName());
        return resource;

    }

    @Override
    @Transactional
    public List<MetaData> getMetaDataByFileName(String fileName) {
        return metaRepo.findByFileName(fileName);
    }

    @Override
    @Transactional
    public List<MetaData> getMetaDataByTime(Date date) {
        return metaRepo.getMetaDataByTime(date);
    }

}

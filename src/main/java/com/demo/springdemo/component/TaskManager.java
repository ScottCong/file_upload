package com.demo.springdemo.component;

import com.demo.springdemo.dao.MetaData;
import com.demo.springdemo.service.MetaService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Component
public class TaskManager {

    private org.slf4j.Logger logger = LoggerFactory.getLogger(TaskManager.class);

    MetaService metaService;

    @Autowired
    public void setMetaService(MetaService metaService) {
        this.metaService = metaService;
    }

    @Scheduled(cron = "0 0 * * * *")
    public void pollNewEntity(){

        Date date = new Date();
        Long time = System.currentTimeMillis();
        time -= 1000 * 60 * 60;
        date.setTime(time);
        List<MetaData> metaDataList = metaService.getMetaDataByTime(date);
        System.out.println(metaDataList.size());
        for(MetaData data: metaDataList){
            System.out.println(data.toString());
            logger.info(data.toString());
        }
    }
}

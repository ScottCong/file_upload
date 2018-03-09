package com.demo.springdemo.repository;

import com.demo.springdemo.dao.MetaData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


public interface MetaRepo extends CrudRepository<MetaData, Long> {
    List<MetaData> findByFileName(String fileName);

    @Query(value = "select * from meta_data as md where md.upload_Date > ?1 ", nativeQuery = true)
    List<MetaData> getMetaDataByTime(Date date);
}

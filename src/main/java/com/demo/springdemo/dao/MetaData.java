package com.demo.springdemo.dao;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
public class MetaData {
    public MetaData(){

    }

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private String fileName;

    @Column
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date uploadDate;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) { this.id = id; }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getFileName() { return fileName; }

    public void setFileName(String fileName) { this.fileName = fileName; }

    @Override
    public String toString() {
        return "MetaData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", uploadDate=" + uploadDate +
                '}';
    }
}

package com.example.demo.model;

import lombok.Data;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Todo implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String title;
    private String detail;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    public Todo(){
        id=0;
    }
    public Todo(String title, String detail) {
        this.title =title;
        this.detail =detail;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }
}

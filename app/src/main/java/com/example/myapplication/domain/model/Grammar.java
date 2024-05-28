package com.example.myapplication.domain.model;

import java.io.Serializable;

public class Grammar implements Serializable {
    private int id;
    private String rule_name;
    private String description;
    private String example;
    private int courseId;
    public Grammar(int id, String rule_name, String description, String example, int courseId){
        this.id = id;
        this.rule_name = rule_name;
        this.description = description;
        this.example = example;
        this.courseId = courseId;
    }
    public  Grammar(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRule_name() {
        return rule_name;
    }

    public void setRule_name(String rule_name) {
        this.rule_name = rule_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}

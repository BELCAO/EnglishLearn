package com.example.myapplication.domain.model;

import java.io.Serializable;

public class Vocabulary implements Serializable {

    private int id;
    private String word;
    private String mean;
    private int courseId;

    public Vocabulary() {

    }

    public Vocabulary(int id, String word, String mean, int courseId) {
        this.id = id;
        this.word = word;
        this.mean = mean;
        this.courseId = courseId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "Vocabulary{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", mean='" + mean + '\'' +
                ", courseId=" + courseId +
                '}';
    }
}

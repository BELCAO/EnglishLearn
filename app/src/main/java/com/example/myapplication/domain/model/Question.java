package com.example.myapplication.domain.model;

import java.io.Serializable;
import java.util.List;

public class Question implements Serializable {
    private String word;
    private List<String> options;
    private String correctAnswer;

    public Question(String word, List<String> options, String correctAnswer) {
        this.word = word;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getWord() {
        return word;
    }

    public List<String> getOptions() {
        return options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}

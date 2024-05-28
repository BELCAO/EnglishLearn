package com.example.myapplication.domain.model;



public class DetailGrammar {

    private int id;
    private int grammarId;
    private String detail;
    private String example;

    public DetailGrammar(int id, int grammarId, String detail, String example) {
        this.id = id;
        this.grammarId = grammarId;
        this.detail = detail;
        this.example = example;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGrammarId() {
        return grammarId;
    }

    public void setGrammarId(int grammarId) {
        this.grammarId = grammarId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    @Override
    public String toString() {
        return "DetailGrammar{" +
                "id=" + id +
                ", grammarId=" + grammarId +
                ", detail='" + detail + '\'' +
                ", example='" + example + '\'' +
                '}';
    }
}


package com.example.myapplication.domain.model;

public class DetailVocabulary {

    private int id;
    private int vocabularyId;
    private String type;
    private String wordMean;
    private String sampleSentence;
    private String sampleSentenceMean;

    public DetailVocabulary(int id, int vocabularyId, String type, String wordMean, String sampleSentence, String sampleSentenceMean) {
        this.id = id;
        this.vocabularyId = vocabularyId;
        this.type = type;
        this.wordMean = wordMean;
        this.sampleSentence = sampleSentence;
        this.sampleSentenceMean = sampleSentenceMean;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVocabularyId() {
        return vocabularyId;
    }

    public void setVocabularyId(int vocabularyId) {
        this.vocabularyId = vocabularyId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWordMean() {
        return wordMean;
    }

    public void setWordMean(String wordMean) {
        this.wordMean = wordMean;
    }

    public String getSampleSentence() {
        return sampleSentence;
    }

    public void setSampleSentence(String sampleSentence) {
        this.sampleSentence = sampleSentence;
    }

    public String getSampleSentenceMean() {
        return sampleSentenceMean;
    }

    public void setSampleSentenceMean(String sampleSentenceMean) {
        this.sampleSentenceMean = sampleSentenceMean;
    }

    @Override
    public String toString() {
        return "DetailVocabulary{" +
                "id=" + id +
                ", vocabularyId=" + vocabularyId +
                ", type='" + type + '\'' +
                ", wordMean='" + wordMean + '\'' +
                ", sampleSentence='" + sampleSentence + '\'' +
                ", sampleSentenceMean='" + sampleSentenceMean + '\'' +
                '}';
    }
}

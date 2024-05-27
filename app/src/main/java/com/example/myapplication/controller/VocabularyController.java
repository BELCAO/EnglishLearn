package com.example.myapplication.controller;

import com.example.myapplication.domain.model.DetailVocabulary;
import com.example.myapplication.domain.model.Vocabulary;
import com.example.myapplication.domain.service.dao.impl.DetailVocabularyDAO;
import com.example.myapplication.domain.service.dao.impl.VocabularyDAO;
import com.example.myapplication.domain.service.database.DatabaseHelper;

import java.util.List;

public class VocabularyController {

    private static VocabularyController instance;
    private DatabaseHelper dbHelper;

    private VocabularyController(DatabaseHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public static VocabularyController getInstance(DatabaseHelper dbHelper) {
        if(instance == null) {
            instance = new VocabularyController(dbHelper);
        }
        return instance;
    }

    /**
     * Tìm danh sách từ vựng của khóa học
     * @param courseId
     * @return
     */
    public List<Vocabulary> find(int courseId) {
        return VocabularyDAO.getInstance(dbHelper).find(courseId);
    }

    /**
     * Tìm danh sách chi tiết từ vựng của 1 từ vựng
     * @param vocabularyId
     * @return
     */
    public List<DetailVocabulary> findDetailVocabulary(int vocabularyId) {
        return DetailVocabularyDAO.getInstance(dbHelper).find(vocabularyId);
    }
}
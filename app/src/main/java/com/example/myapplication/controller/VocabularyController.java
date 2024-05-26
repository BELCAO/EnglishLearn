package com.example.myapplication.controller;

import com.example.myapplication.domain.model.Vocabulary;
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

    public List<Vocabulary> find(int courseId) {
        return VocabularyDAO.getInstance(dbHelper).find(courseId);
    }
}

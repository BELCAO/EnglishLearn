package com.example.myapplication.controller;

import com.example.myapplication.domain.model.DetailGrammar;
import com.example.myapplication.domain.model.DetailVocabulary;
import com.example.myapplication.domain.model.Grammar;
import com.example.myapplication.domain.model.Vocabulary;
import com.example.myapplication.domain.service.dao.impl.DetailGrammarDAO;
import com.example.myapplication.domain.service.dao.impl.DetailVocabularyDAO;
import com.example.myapplication.domain.service.dao.impl.GrammarDAO;
import com.example.myapplication.domain.service.dao.impl.VocabularyDAO;
import com.example.myapplication.domain.service.database.DatabaseHelper;

import java.util.List;

public class GrammarController {

    private static GrammarController instance;
    private DatabaseHelper dbHelper;

    public GrammarController(DatabaseHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public static GrammarController getInstance(DatabaseHelper dbHelper) {
        if(instance == null) {
            instance = new GrammarController(dbHelper);
        }
        return instance;
    }

    public List<Grammar> find(int courseId) {
        return GrammarDAO.getInstance(dbHelper).find(courseId);
    }

    public List<DetailGrammar> findDetailGrammar(int grammarId) {
        return DetailGrammarDAO.getInstance(dbHelper).find(grammarId);
    }

}

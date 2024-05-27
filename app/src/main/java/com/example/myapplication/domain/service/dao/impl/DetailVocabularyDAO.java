package com.example.myapplication.domain.service.dao.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.domain.model.DetailVocabulary;
import com.example.myapplication.domain.model.Vocabulary;
import com.example.myapplication.domain.service.dao.interfaces.IDetailVocabularyDAO;
import com.example.myapplication.domain.service.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class DetailVocabularyDAO implements IDetailVocabularyDAO {

    private DatabaseHelper dbHelper;

    private DetailVocabularyDAO() {

    }

    private DetailVocabularyDAO(DatabaseHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public static DetailVocabularyDAO getInstance(DatabaseHelper dbHelper) {
        return DetailVocabularyDAO.DetailVocabularyDAOHelper.getInstance(dbHelper);
    }

    @Override
    public void insert(DetailVocabulary detailVocabulary) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("vocabularyId", detailVocabulary.getVocabularyId());
        values.put("type", detailVocabulary.getType());
        values.put("wordMean", detailVocabulary.getWordMean());
        values.put("sampleSentence", detailVocabulary.getSampleSentence());
        values.put("sampleSentenceMean", detailVocabulary.getSampleSentenceMean());

        db.insert("vocabulary_details", null, values);

        db.close();
    }

    @Override
    public List<DetailVocabulary> findAll() {
        return null;
    }

    @Override
    public List<DetailVocabulary> find(int vocabularyId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<DetailVocabulary> detailVocabularyList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM vocabulary_details WHERE vocabularyId=?",
                new String[] {String.valueOf(vocabularyId)});

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String type = cursor.getString(2);
            String wordMean = cursor.getString(3);
            String sampleSentence = cursor.getString(4);
            String sampleSentenceMean = cursor.getString(5);

            detailVocabularyList.add(new DetailVocabulary(id, vocabularyId, type, wordMean, sampleSentence, sampleSentenceMean));
        }
        db.close();
        cursor.close();
        return detailVocabularyList;
    }

    @Override
    public int update(DetailVocabulary detailVocabulary) {
        return 0;
    }

    @Override
    public int delete(DetailVocabulary detailVocabulary) {
        return 0;
    }

    private static class DetailVocabularyDAOHelper {
        private static DetailVocabularyDAO INSTANCE;

        public static DetailVocabularyDAO getInstance(DatabaseHelper dbHelper) {
            if(INSTANCE == null) {
                INSTANCE = new DetailVocabularyDAO(dbHelper);
            }
            return INSTANCE;
        }
    }
}

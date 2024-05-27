package com.example.myapplication.domain.service.dao.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.domain.model.Vocabulary;
import com.example.myapplication.domain.service.dao.interfaces.IVocabularyDAO;
import com.example.myapplication.domain.service.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class VocabularyDAO implements IVocabularyDAO {

    private DatabaseHelper dbHelper;

    private VocabularyDAO() {

    }

    private VocabularyDAO(DatabaseHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public static VocabularyDAO getInstance(DatabaseHelper dbHelper) {
        return VocabularyDAO.VocabularyDAOHelper.getInstance(dbHelper);
    }

    @Override
    public void insert(Vocabulary vocabulary) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("word", vocabulary.getWord());
        values.put("mean", vocabulary.getMean());
        values.put("phonetic", vocabulary.getPhonetic());
        values.put("courseId", vocabulary.getCourseId());

        db.insert("vocabularies", null, values);

        db.close();
    }

    @Override
    public List<Vocabulary> findAll() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<Vocabulary> vocabularyList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM vocabularies", null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String word = cursor.getString(1);
            String mean = cursor.getString(2);
            int courseId = cursor.getInt(3);

            Vocabulary v = new Vocabulary(id, word, mean, courseId);
            vocabularyList.add(v);
        }
        db.close();
        cursor.close();
        return vocabularyList;
    }

    @Override
    public List<Vocabulary> find(int courseId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<Vocabulary> vocabularyList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM vocabularies WHERE courseId=?",
                new String[] {String.valueOf(courseId)});

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String word = cursor.getString(1);
            String mean = cursor.getString(2);
            String phonetic = cursor.getString(3);

            Vocabulary v = new Vocabulary(id, word, mean, phonetic, courseId);
            vocabularyList.add(v);
        }
        db.close();
        cursor.close();
        return vocabularyList;
    }

    @Override
    public int update(Vocabulary vocabulary) {
        return 0;
    }

    @Override
    public int delete(Vocabulary vocabulary) {
        return 0;
    }

    private static class VocabularyDAOHelper {
        private static VocabularyDAO INSTANCE;

        public static VocabularyDAO getInstance(DatabaseHelper dbHelper) {
            if(INSTANCE == null) {
                INSTANCE = new VocabularyDAO(dbHelper);
            }
            return INSTANCE;
        }
    }
}

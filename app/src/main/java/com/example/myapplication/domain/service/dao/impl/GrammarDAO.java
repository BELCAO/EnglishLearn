package com.example.myapplication.domain.service.dao.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.domain.model.Grammar;
import com.example.myapplication.domain.service.dao.interfaces.IGrammarDAO;
import com.example.myapplication.domain.service.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class GrammarDAO implements IGrammarDAO {
    private  static GrammarDAO instance;
    private DatabaseHelper dbHelper;

    private GrammarDAO() {

    }

    private GrammarDAO(DatabaseHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public static GrammarDAO getInstance(DatabaseHelper dbHelper) {
        if (instance == null) {
            instance = new GrammarDAO(dbHelper);
        }
        return instance;
    }

    @Override
    public void insert(Grammar grammarRule) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("rule_name", grammarRule.getRule_name());
        values.put("description", grammarRule.getDescription());
        values.put("example", grammarRule.getExample());
        values.put("courseId", grammarRule.getCourseId());

        db.insert("grammars ", null, values);

        db.close();
    }

    @Override
    public List<Grammar> findAll() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<Grammar> grammarRuleList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM gammars", null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String rule_name = cursor.getString(1);
            String description = cursor.getString(2);
            String example = cursor.getString(3);
            int courseId = cursor.getInt(4);

            Grammar grammarRule = new Grammar(id, rule_name, description, example, courseId);
            grammarRuleList.add(grammarRule);
        }
        db.close();
        cursor.close();
        return grammarRuleList;
    }

    @Override
    public List<Grammar> find(int courseId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<Grammar> grammarRuleList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM grammars WHERE courseId=?",
                new String[]{String.valueOf(courseId)});

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String rule_name = cursor.getString(1);
            String description = cursor.getString(2);
            String example = cursor.getString(3);

            Grammar grammarRule = new Grammar(id, rule_name, description, example, courseId);
            grammarRuleList.add(grammarRule);
        }
        db.close();
        cursor.close();
        return grammarRuleList;
    }

    @Override
    public int update(Grammar grammarRule) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("rule_name", grammarRule.getRule_name());
        values.put("description", grammarRule.getDescription());
        values.put("example", grammarRule.getExample());
        values.put("courseId", grammarRule.getCourseId());

        int rowsAffected = db.update("grammars", values, "id = ?",
                new String[]{String.valueOf(grammarRule.getId())});

        db.close();
        return rowsAffected;
    }

    @Override
    public int delete(Grammar grammarRule) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        int rowsDeleted = db.delete("grammar", "id = ?",
                new String[]{String.valueOf(grammarRule.getId())});

        db.close();
        return rowsDeleted;
    }

    private static class GrammarDAOHelper {
        private static GrammarDAO INSTANCE;

        public static GrammarDAO getInstance(DatabaseHelper dbHelper) {
            if (INSTANCE == null) {
                INSTANCE = new GrammarDAO(dbHelper);
            }
            return INSTANCE;
        }
    }
}

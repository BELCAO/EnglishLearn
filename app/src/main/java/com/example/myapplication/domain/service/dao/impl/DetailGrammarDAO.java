package com.example.myapplication.domain.service.dao.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.domain.model.DetailGrammar;
import com.example.myapplication.domain.service.dao.interfaces.IDetailGrammarDAO;
import com.example.myapplication.domain.service.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class DetailGrammarDAO implements IDetailGrammarDAO {

    private DatabaseHelper dbHelper;

    private DetailGrammarDAO() {

    }

    private DetailGrammarDAO(DatabaseHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public static DetailGrammarDAO getInstance(DatabaseHelper dbHelper) {
        return DetailGrammarDAOHelper.getInstance(dbHelper);
    }

    @Override
    public void insert(DetailGrammar detailGrammar) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("grammarId", detailGrammar.getGrammarId());
        values.put("detail", detailGrammar.getDetail());
        values.put("example", detailGrammar.getExample());

        db.insert("grammar_details", null, values);

        db.close();
    }

    @Override
    public List<DetailGrammar> findAll() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<DetailGrammar> detailGrammarList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM grammar_details", null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            int grammarId = cursor.getInt(1);
            String detail = cursor.getString(2);
            String example = cursor.getString(3);

            detailGrammarList.add(new DetailGrammar(id, grammarId, detail, example));
        }
        db.close();
        cursor.close();
        return detailGrammarList;
    }

    @Override
    public List<DetailGrammar> find(int grammarId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<DetailGrammar> detailGrammarList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM grammar_details WHERE grammarId=?",
                new String[] {String.valueOf(grammarId)});

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String detail = cursor.getString(2);
            String example = cursor.getString(3);

            detailGrammarList.add(new DetailGrammar(id, grammarId, detail, example));
        }
        db.close();
        cursor.close();
        return detailGrammarList;
    }

    @Override
    public int update(DetailGrammar detailGrammar) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("grammarId", detailGrammar.getGrammarId());
        values.put("detail", detailGrammar.getDetail());
        values.put("example", detailGrammar.getExample());

        int rowsAffected = db.update("grammar_details", values, "id = ?",
                new String[]{String.valueOf(detailGrammar.getId())});

        db.close();
        return rowsAffected;
    }

    @Override
    public int delete(DetailGrammar detailGrammar) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        int rowsDeleted = db.delete("grammar_details", "id = ?",
                new String[]{String.valueOf(detailGrammar.getId())});

        db.close();
        return rowsDeleted;
    }

    private static class DetailGrammarDAOHelper {
        private static DetailGrammarDAO INSTANCE;

        public static DetailGrammarDAO getInstance(DatabaseHelper dbHelper) {
            if(INSTANCE == null) {
                INSTANCE = new DetailGrammarDAO(dbHelper);
            }
            return INSTANCE;
        }
    }
}

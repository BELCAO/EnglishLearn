package com.example.myapplication.domain.service.dao.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.domain.model.Course;
import com.example.myapplication.domain.service.dao.interfaces.ICourseDAO;
import com.example.myapplication.domain.service.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class CourseDAO implements ICourseDAO {

    private DatabaseHelper dbHelper;

    private CourseDAO() {

    }

    private CourseDAO(DatabaseHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public static CourseDAO getInstance(DatabaseHelper dbHelper) {
        return CourseDAOHelper.getInstance(dbHelper);
    }

    @Override
    public void insert(Course course) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", course.getName());
        values.put("price", course.getPrice());

        db.insert("courses", null, values);

        db.close();
    }

    @Override
    public List<Course> findAll() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<Course> courses = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM courses", null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int price = cursor.getInt(2);

            Course c = new Course(id, name, price);
            courses.add(c);
        }

        db.close();
        return courses;
    }

    @Override
    public int update(Course course) {
        return 0;
    }

    @Override
    public int delete(Course course) {
        return 0;
    }

    private static class CourseDAOHelper {
        private static CourseDAO INSTANCE;

        public static CourseDAO getInstance(DatabaseHelper dbHelper) {
            if(INSTANCE == null) {
                INSTANCE = new CourseDAO(dbHelper);
            }
            return INSTANCE;
        }
    }
}

package com.example.myapplication.controller;

import com.example.myapplication.domain.model.Course;
import com.example.myapplication.domain.model.Vocabulary;
import com.example.myapplication.domain.service.dao.impl.CourseDAO;
import com.example.myapplication.domain.service.database.DatabaseHelper;

import java.util.List;

public class CourseController {
    private static CourseController instance;
    private DatabaseHelper dbHelper;

    private CourseController(DatabaseHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public static CourseController getInstance(DatabaseHelper dbHelper) {
        if(instance == null) {
            instance = new CourseController(dbHelper);
        }
        return instance;
    }

    public List<Course> findAll() {
        return CourseDAO.getInstance(dbHelper).findAll();
    }
}

package com.example.myapplication.domain.service.database;

import static androidx.test.InstrumentationRegistry.getContext;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.domain.model.Course;
import com.example.myapplication.domain.model.Vocabulary;
import com.example.myapplication.domain.service.dao.impl.CourseDAO;
import com.example.myapplication.domain.service.dao.impl.VocabularyDAO;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "englearn.db";
    public static final int DB_VERSION = 1;
    private static final String PREF_CREATED_DATA = "created_data";

    private DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static DatabaseHelper getInstance(Context context) {
        return DatabaseInstance.getInstance(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createCourses(db);
        createVocabulary(db);
    }

    public void createData() {
        CourseDAO courseDAO = CourseDAO.getInstance(this);
        courseDAO.insert(new Course(1, "TOEIC (cơ bản)", 0));
        courseDAO.insert(new Course(2, "TOEIC (350 - 650)", 1000000));
        courseDAO.insert(new Course(3, "TOEIC (650 - 800)", 2000000));
        courseDAO.insert(new Course(4, "TOEIC (800+)", 2500000));

        VocabularyDAO vocabularyDAO = VocabularyDAO.getInstance(this);
        vocabularyDAO.insert(new Vocabulary(1, "Order", "Đơn hàng / đặt hàng (n)", 1));
        vocabularyDAO.insert(new Vocabulary(2, "Subject", "Chủ đề (n)", 1));
        vocabularyDAO.insert(new Vocabulary(3, "Achievement", "Thành tựu (n)", 1));
        vocabularyDAO.insert(new Vocabulary(4, "Boundary", "Ranh giới (n)", 1));
        vocabularyDAO.insert(new Vocabulary(5, "Challenge", "Thách thức (n)", 1));
        vocabularyDAO.insert(new Vocabulary(6, "Diversity", "Sự đa dạng (n)", 1));
        vocabularyDAO.insert(new Vocabulary(7, "Efficiency", "Hiệu suất (n)", 1));
        vocabularyDAO.insert(new Vocabulary(8, "Flexibility", "Tính linh hoạt (n)", 1));
        vocabularyDAO.insert(new Vocabulary(9, "Growth", "Sự phát triển (n)", 1));
        vocabularyDAO.insert(new Vocabulary(10, "Harmony", "Sự hòa thuận (n)", 1));
        vocabularyDAO.insert(new Vocabulary(11, "Innovation", "Sự đổi mới (n)", 1));
        vocabularyDAO.insert(new Vocabulary(12, "Knowledge", "Kiến thức (n)", 1));
        vocabularyDAO.insert(new Vocabulary(13, "Leadership", "Lãnh đạo (n)", 1));
        vocabularyDAO.insert(new Vocabulary(14, "Motivation", "Dong viên (n)", 1));
        vocabularyDAO.insert(new Vocabulary(15, "Opportunity", "Cơ hội (n)", 1));
        vocabularyDAO.insert(new Vocabulary(16, "Perseverance", "Sự kiên trì (n)", 1));
        vocabularyDAO.insert(new Vocabulary(17, "Quality", "Chất lượng (n)", 1));
        vocabularyDAO.insert(new Vocabulary(18, "Resilience", "Sự đàn hồi (n)", 1));
        vocabularyDAO.insert(new Vocabulary(19, "Sustainability", "Tính bền vững (n)", 1));
        vocabularyDAO.insert(new Vocabulary(20, "Teamwork", "Làm việc nhóm (n)", 1));
        vocabularyDAO.insert(new Vocabulary(21, "Understanding", "Sự hiểu biết (n)", 1));
        vocabularyDAO.insert(new Vocabulary(22, "Value", "Giá trị (n)", 1));
        vocabularyDAO.insert(new Vocabulary(23, "Wisdom", "Sự khôn ngoan (n)", 1));
        vocabularyDAO.insert(new Vocabulary(24, "Xenophobia", "Kỳ thị người nước ngoài (n)", 1));
        vocabularyDAO.insert(new Vocabulary(25, "Youth", "Tuổi trẻ (n)", 1));
        vocabularyDAO.insert(new Vocabulary(26, "Zeal", "Nhiệt huyết (n)", 1));
        vocabularyDAO.insert(new Vocabulary(27, "Revolution", "Cuộc cách mạng (n)", 1));
        vocabularyDAO.insert(new Vocabulary(28, "Revolutionary", "Mang tính cách mạng (adj)", 1));
        vocabularyDAO.insert(new Vocabulary(29, "Exploration", "Sự khám phá (n)", 1));
        vocabularyDAO.insert(new Vocabulary(30, "Exploratory", "Mang tính khám phá (adj)", 1));

        vocabularyDAO.insert(new Vocabulary(31, "Artificial Intelligence", "Trí tuệ nhân tạo (n)", 2));
        vocabularyDAO.insert(new Vocabulary(32, "Blockchain", "Chuỗi khối (n)", 2));
        vocabularyDAO.insert(new Vocabulary(33, "Cybersecurity", "An ninh mạng (n)", 2));
        vocabularyDAO.insert(new Vocabulary(34, "Big Data", "Dữ liệu lớn (n)", 2));
        vocabularyDAO.insert(new Vocabulary(35, "Cloud Computing", "Máy chủ đám mây (n)", 2));
        vocabularyDAO.insert(new Vocabulary(36, "Internet of Things (IoT)", "Internet của mọi vật (n)", 2));
        vocabularyDAO.insert(new Vocabulary(37, "Machine Learning", "Học máy (n)", 2));
        vocabularyDAO.insert(new Vocabulary(38, "Virtual Reality", "Thực tế ảo (n)", 2));
        vocabularyDAO.insert(new Vocabulary(39, "Augmented Reality", "Thực tế tăng cường (n)", 2));
        vocabularyDAO.insert(new Vocabulary(40, "Data Science", "Khoa học dữ liệu (n)", 2));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void createCourses(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS courses (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "price INTEGER)";

        db.execSQL(query);
    }

    private void createVocabulary(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS vocabularies (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "word TEXT," +
                "mean TEXT," +
                "courseId INTEGER CONSTRAINT courseId REFERENCES courses(id) ON DELETE CASCADE)";

        db.execSQL(query);
    }


    private static class DatabaseInstance {
        private static DatabaseHelper INSTANCE;

        private static DatabaseHelper getInstance(Context  context) {
            if(INSTANCE == null) {
                INSTANCE = new DatabaseHelper(context);
            }
            return INSTANCE;
        }
    }
}

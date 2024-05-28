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
import com.example.myapplication.domain.model.DetailVocabulary;
import com.example.myapplication.domain.model.Vocabulary;
import com.example.myapplication.domain.service.dao.impl.CourseDAO;
import com.example.myapplication.domain.service.dao.impl.DetailVocabularyDAO;
import com.example.myapplication.domain.service.dao.impl.VocabularyDAO;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "englearn.db";
    public static final int DB_VERSION = 1;

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
        createVocabularyDetail(db);
    }

    /**
     * Insert dữ liệu vào database
     */
    public void createData() {
        CourseDAO courseDAO = CourseDAO.getInstance(this);
        courseDAO.insert(new Course(1, "TOEIC (cơ bản)", 0));
        courseDAO.insert(new Course(2, "TOEIC (350 - 650)", 1000000));
        courseDAO.insert(new Course(3, "TOEIC (650 - 800)", 2000000));
        courseDAO.insert(new Course(4, "TOEIC (800+)", 2500000));

        VocabularyDAO vocabularyDAO = VocabularyDAO.getInstance(this);
        vocabularyDAO.insert(new Vocabulary(1, "Subject", "Chủ đề (n)", "/ˈsʌb.dʒɪkt/", 1));
        vocabularyDAO.insert(new Vocabulary(2, "Open", "Mở (n)", "/ˈoʊpən/", 1));
        vocabularyDAO.insert(new Vocabulary(3, "Purpose", "Mục đích (n)", "/ˈpɝː.pəs/", 1));
        vocabularyDAO.insert(new Vocabulary(4, "Report", "Báo cáo (v)", "/rɪˈpɔːrt/", 1));
        vocabularyDAO.insert(new Vocabulary(5, "Project", "Dự án (n)", "/ˈprɑː.dʒekt/", 1));
        vocabularyDAO.insert(new Vocabulary(6, "Development", "Phát triển (n)", "/dɪˈvel.əp.mənt/", 1));
        vocabularyDAO.insert(new Vocabulary(7, "Requirement", "Yêu cầu (n)", "/rɪˈkwaɪər.mənt/", 1));
        vocabularyDAO.insert(new Vocabulary(8, "Budget", "Ngân sách (n)", "/ˈbʌdʒ.ɪt/", 1));
        vocabularyDAO.insert(new Vocabulary(9, "Meeting", "Cuộc họp (n)", "/ˈmiː.tɪŋ/", 1));
        vocabularyDAO.insert(new Vocabulary(10, "Schedule", "Lịch trình (n)", "/ˈskedʒ.uːl/", 1));
        vocabularyDAO.insert(new Vocabulary(11, "Analysis", "Phân tích (n)", "/əˈnæl.ə.sɪs/", 1));
        vocabularyDAO.insert(new Vocabulary(12, "Resource", "Tài nguyên (n)", "/ˈriː.sɔːrs/", 1));
        vocabularyDAO.insert(new Vocabulary(13, "Plan", "Kế hoạch (n)", "/plæn/", 1));
        vocabularyDAO.insert(new Vocabulary(14, "Strategy", "Chiến lược (n)", "/ˈstræt.ə.dʒi/", 1));
        vocabularyDAO.insert(new Vocabulary(15, "Team", "Nhóm (n)", "/tiːm/", 1));
        vocabularyDAO.insert(new Vocabulary(16, "Client", "Khách hàng (n)", "/ˈklaɪ.ənt/", 1));
        vocabularyDAO.insert(new Vocabulary(17, "Feedback", "Phản hồi (n)", "/ˈfiːd.bæk/", 1));
        vocabularyDAO.insert(new Vocabulary(18, "Improvement", "Cải tiến (n)", "/ɪmˈpruːv.mənt/", 1));
        vocabularyDAO.insert(new Vocabulary(19, "Performance", "Hiệu suất (n)", "/pərˈfɔːr.məns/", 1));
        vocabularyDAO.insert(new Vocabulary(20, "Objective", "Mục tiêu (n)", "/əbˈdʒek.tɪv/", 1));

        vocabularyDAO.insert(new Vocabulary(1, "Absorb", "Hấp thụ (v)", "/əbˈsɔːrb/", 2));
        vocabularyDAO.insert(new Vocabulary(2, "Challenge", "Thách thức (n)", "/ˈtʃæl.ɪndʒ/", 2));
        vocabularyDAO.insert(new Vocabulary(3, "Contribute", "Đóng góp (v)", "/kənˈtrɪb.juːt/", 2));
        vocabularyDAO.insert(new Vocabulary(4, "Delegate", "Ủy quyền (v)", "/ˈdel.ɪ.ɡeɪt/", 2));
        vocabularyDAO.insert(new Vocabulary(5, "Emerge", "Nổi lên (v)", "/ɪˈmɜːrdʒ/", 2));
        vocabularyDAO.insert(new Vocabulary(6, "Generate", "Tạo ra (v)", "/ˈdʒen.ə.reɪt/", 2));
        vocabularyDAO.insert(new Vocabulary(7, "Innovate", "Đổi mới (v)", "/ˈɪn.ə.veɪt/", 2));
        vocabularyDAO.insert(new Vocabulary(8, "Persuade", "Thuyết phục (v)", "/pərˈsweɪd/", 2));
        vocabularyDAO.insert(new Vocabulary(9, "Revise", "Sửa đổi (v)", "/rɪˈvaɪz/", 2));
        vocabularyDAO.insert(new Vocabulary(10, "Simulate", "Mô phỏng (v)", "/ˈsɪm.jə.leɪt/", 2));

        DetailVocabularyDAO detailVocabularyDAO = DetailVocabularyDAO.getInstance(this);
        detailVocabularyDAO.insert(
                new DetailVocabulary(1, 1, "Danh từ", "Chủ đề, vấn đề, đề tài",
                        "Historical subject", "Chủ đề lịch sử"));
        detailVocabularyDAO.insert(
                new DetailVocabulary(2, 1, "Tính từ", "Lệ thuộc, ở dưới quyền",
                        "The subject nations", "Những nước lệ thuộc"));
        detailVocabularyDAO.insert(
                new DetailVocabulary(3, 1, "Ngoại động từ", "Chinh phục, khuất phục",
                        "Must be subjected to great heat", "Phải chịu một độ nhiệt cao"));

        detailVocabularyDAO.insert(
                new DetailVocabulary(1, 2, "Danh từ", "Chỗ ngoài trời, chỗ thoáng mát",
                        "In the open", "Ở ngoài trời, giữa thanh thiên bạch nhật"));
        detailVocabularyDAO.insert(
                new DetailVocabulary(2, 2, "Tính từ", "Mở, ngỏ",
                        "An open letter", "Bức thư ngỏ"));
        detailVocabularyDAO.insert(
                new DetailVocabulary(3, 2, "Ngoại động từ", "Mở, bắt đầu, khai mạc",
                        "To open a business", "Bắt đầu kinh doanh"));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * Tạo bảng courses
     * @param db
     */
    private void createCourses(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS courses (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "price INTEGER)";

        db.execSQL(query);
    }

    /**
     * Tạo bảng vocabulary_details
     * @param db
     */
    private void createVocabularyDetail(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS vocabulary_details (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "vocabularyId INTEGER, " +
                "type TEXT, " +
                "wordMean TEXT, " +
                "sampleSentence TEXT, " +
                "sampleSentenceMean TEXT, " +
                "FOREIGN KEY(vocabularyId) REFERENCES vocabularies(id) ON DELETE CASCADE" +
                ")";

        db.execSQL(query);
    }

    /**
     * Tạo bảng vocabularies
     * @param db
     */
    private void createVocabulary(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS vocabularies (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "word TEXT," +
                "mean TEXT," +
                "phonetic TEXT," +
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

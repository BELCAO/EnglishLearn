package com.example.myapplication.service.dao.impl;

import static org.junit.Assert.assertTrue;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.myapplication.domain.model.Vocabulary;
import com.example.myapplication.domain.service.dao.impl.VocabularyDAO;
import com.example.myapplication.domain.service.database.DatabaseHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class TestVocabularyDAO {

    private VocabularyDAO vocabularyDAO;
    private DatabaseHelper db;
    private static boolean isCreatedData;

    /**
     * Tạo database helper dựa vào context ảo
     * Tạo bảng và insert data vào để test
     */
    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        db = DatabaseHelper.getInstance(context);

        // Tránh insert dữ liệu lại mỗi khi run 1 @Test
        if(!isCreatedData) {
            db.createData();
            isCreatedData = true;
        }

        vocabularyDAO = VocabularyDAO.getInstance(db);
    }

    /**
     * Tự động ngắt kết nối db và xóa data sau khi test
     */
    @After
    public void closeDb() {
        db.close();
    }

    /**
     * Test find all vocabulary
     */
    @Test
    public void testFindAll() {
        List<Vocabulary> vocabularyList = vocabularyDAO.findAll();
        assertTrue(vocabularyList.size() > 0);
    }

    /**
     * Test find vocabulary of course by courseId
     * Their have 20 vocabulary of the course with courseId = 1
     */
    @Test
    public void testFind() {
        List<Vocabulary> vocabularyList = vocabularyDAO.find(1);
        assertTrue(vocabularyList.size() == 20);
    }
}
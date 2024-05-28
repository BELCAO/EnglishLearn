package com.example.myapplication.service.dao.impl;

import static org.junit.Assert.assertTrue;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.myapplication.domain.model.DetailVocabulary;
import com.example.myapplication.domain.service.dao.impl.DetailVocabularyDAO;
import com.example.myapplication.domain.service.database.DatabaseHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class TestDetailVocabularyDAO {

    private DatabaseHelper db;
    private DetailVocabularyDAO detailVocabularyDAO;
    private static boolean isCreatedData;

    /**
     * Tạo database helper dựa vào context ảo
     * Tạo bảng và insert data vào để test
     */
    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        db = DatabaseHelper.getInstance(context);

        if(!isCreatedData) {
            db.createData();
            isCreatedData = true;
        }

        detailVocabularyDAO = DetailVocabularyDAO.getInstance(db);
    }

    /**
     * Tự động ngắt kết nối db và xóa data sau khi test
     */
    @After
    public void closeDb() {
        db.close();
    }

    /**
     * Test find detail vocabulary of vocabulary by vocabularyId
     * Their have 3 detail vocabulary of the vocabulary with vocabularyId = 1
     */
    @Test
    public void testFind() {
        List<DetailVocabulary> detailVocabularyList = detailVocabularyDAO.find(1);
        assertTrue(detailVocabularyList.size() == 3);
    }
}

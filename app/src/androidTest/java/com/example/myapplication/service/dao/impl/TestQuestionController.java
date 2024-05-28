package com.example.myapplication.service.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.myapplication.controller.QuestionController;
import com.example.myapplication.domain.model.Question;
import com.example.myapplication.domain.model.Vocabulary;
import com.example.myapplication.domain.service.database.DatabaseHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class TestQuestionController {

    private QuestionController questionController;
    private DatabaseHelper db;

    @Before
    public void setUp() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        db = DatabaseHelper.getInstance(context);
        questionController = new QuestionController(db);

        // Tạo dữ liệu mẫu
        db.createData();
    }

    @After
    public void tearDown() {
        db.close();
    }

    @Test
    public void testGenerateQuestions() {
        List<Vocabulary> vocabularyList = new ArrayList<>();
        vocabularyList.add(new Vocabulary(1, "Test1", "Meaning1", 1));
        vocabularyList.add(new Vocabulary(2, "Test2", "Meaning2", 1));

        List<Question> questions = questionController.generateQuestions(vocabularyList, 2);

        assertNotNull(questions);
        assertEquals(2, questions.size());

        for (Question question : questions) {
            assertNotNull(question.getWord());
            assertNotNull(question.getOptions());
            assertEquals(4, question.getOptions().size());
        }
    }
}

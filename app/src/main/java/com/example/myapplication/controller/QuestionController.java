package com.example.myapplication.controller;

import com.example.myapplication.domain.model.Question;
import com.example.myapplication.domain.model.Vocabulary;
import com.example.myapplication.domain.service.dao.impl.VocabularyDAO;
import com.example.myapplication.domain.service.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionController {

    private VocabularyDAO vocabularyDAO;

    public QuestionController(DatabaseHelper dbHelper) {
        this.vocabularyDAO = VocabularyDAO.getInstance(dbHelper);
    }

    public List<Question> generateQuestions(List<Vocabulary> vocabularyList, int numQuestions) {
        List<Question> questions = new ArrayList<>();
        List<Vocabulary> allVocabularies = vocabularyDAO.findAll();

        // Nếu tổng số từ vựng ít hơn 4, không thể tạo đủ câu hỏi
        if (allVocabularies.size() < 4) {
            return questions;
        }

        for (Vocabulary vocab : vocabularyList) {
            List<String> options = new ArrayList<>();
            options.add(vocab.getMean());

            // Trộn từ vựng với 3 từ khác từ toàn bộ từ vựng
            Collections.shuffle(allVocabularies);
            int count = 0;
            for (Vocabulary otherVocab : allVocabularies) {
                if (!otherVocab.getWord().equals(vocab.getWord()) && !options.contains(otherVocab.getMean())) {
                    options.add(otherVocab.getMean());
                    count++;
                    if (count == 3) {
                        break;
                    }
                }
            }

            // Kiểm tra nếu không đủ 4 tùy chọn thì bỏ qua câu hỏi này
            if (options.size() != 4) {
                continue;
            }

            // Shuffle options để trộn vị trí các đáp án
            Collections.shuffle(options);

            questions.add(new Question(vocab.getWord(), options, vocab.getMean()));
        }

        // Chỉ lấy số lượng câu hỏi cần thiết
        return questions.size() > numQuestions ? questions.subList(0, numQuestions) : questions;
    }
}

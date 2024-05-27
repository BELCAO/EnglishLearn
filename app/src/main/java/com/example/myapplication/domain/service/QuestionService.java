package com.example.myapplication.domain.service;

import com.example.myapplication.domain.model.Question;
import com.example.myapplication.domain.model.Vocabulary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class QuestionService {
    public List<Question> generateQuestions(List<Vocabulary> vocabularyList, int numberOfQuestions) {
        List<Question> questions = new ArrayList<>();
        Random random = new Random();

        // Shuffle vocabulary list to ensure random selection
        Collections.shuffle(vocabularyList);

        for (int i = 0; i < numberOfQuestions; i++) {
            Vocabulary vocab = vocabularyList.get(i);
            List<String> options = new ArrayList<>();
            options.add(vocab.getMean());

            // Add three random options
            while (options.size() < 4) {
                String randomOption = vocabularyList.get(random.nextInt(vocabularyList.size())).getMean();
                if (!options.contains(randomOption)) {
                    options.add(randomOption);
                }
            }

            // Shuffle options to randomize their order
            Collections.shuffle(options);

            // Create question
            Question question = new Question(vocab.getWord(), options, vocab.getMean());
            questions.add(question);
        }

        return questions;
    }
}

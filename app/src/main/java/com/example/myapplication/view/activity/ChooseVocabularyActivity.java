package com.example.myapplication.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.domain.model.Vocabulary;

import java.util.List;

public class ChooseVocabularyActivity extends AppCompatActivity {

    private List<Vocabulary> vocabularyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_vocabulary);

        // Lấy danh sách từ vựng cần luyện trong bài học
        Intent intent = getIntent();
        vocabularyList = (List<Vocabulary>) intent.getSerializableExtra("list_vocabulary");
    }
}
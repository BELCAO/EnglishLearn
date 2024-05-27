package com.example.myapplication.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.controller.VocabularyController;
import com.example.myapplication.domain.model.Vocabulary;
import com.example.myapplication.domain.service.database.DatabaseHelper;
import com.example.myapplication.view.adapter.DetailVocabularyAdapter;

public class DetailVocabularyActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView rcvDetailVocabulary;
    private DetailVocabularyAdapter detailVocabularyAdapter;
    private DatabaseHelper dbHelper;
    private Vocabulary vocabulary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_vocabulary);

        dbHelper = DatabaseHelper.getInstance(this);

        Intent intent = getIntent();
        vocabulary = (Vocabulary) intent.getSerializableExtra("vocabulary");
        initView();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        rcvDetailVocabulary = findViewById(R.id.rcv_detail_vocabulary);

        toolbar.setNavigationOnClickListener(v -> finish());

        initVocabulary(vocabulary);
        initRcvDetailVocabulary(vocabulary.getId());
    }

    private void initVocabulary(Vocabulary vocabulary) {
        TextView tvWord = findViewById(R.id.tv_word);
        tvWord.setText(vocabulary.getWord());

        TextView tvPhonetic = findViewById(R.id.tv_phonetic);
        tvPhonetic.setText(vocabulary.getPhonetic());

        toolbar.setTitle(vocabulary.getWord());
    }

    private void initRcvDetailVocabulary(int vocabularyId) {
        detailVocabularyAdapter = new DetailVocabularyAdapter(VocabularyController.getInstance(dbHelper)
                .findDetailVocabulary(vocabularyId));

        rcvDetailVocabulary.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rcvDetailVocabulary.setAdapter(detailVocabularyAdapter);
    }
}
package com.example.myapplication.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.controller.GrammarController;
import com.example.myapplication.controller.VocabularyController;
import com.example.myapplication.domain.model.Grammar;
import com.example.myapplication.domain.model.Vocabulary;
import com.example.myapplication.domain.service.database.DatabaseHelper;
import com.example.myapplication.view.adapter.DetailGrammarAdapter;
import com.example.myapplication.view.adapter.DetailVocabularyAdapter;

public class DetailGrammarActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView rcvDetailGrammar;
    private DetailGrammarAdapter detailGrammarAdapterAdapter;
    private DatabaseHelper dbHelper;
    private Grammar grammar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_grammar);

        dbHelper = DatabaseHelper.getInstance(this);

        Intent intent = getIntent();
        grammar = (Grammar) intent.getSerializableExtra("grammar");
        initView();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        rcvDetailGrammar = findViewById(R.id.rcv_detail_grammar);

        toolbar.setNavigationOnClickListener(v -> finish());

        initVocabulary(grammar);
        initRcvDetailVocabulary(grammar.getId());
    }

    private void initVocabulary(Grammar grammar) {
        TextView tvWord = findViewById(R.id.tv_grammar);
        tvWord.setText(grammar.getRule_name());

        TextView tvPhonetic = findViewById(R.id.tv_phonetic);
        tvPhonetic.setText(grammar.getDescription());

        toolbar.setTitle(grammar.getExample());
    }

    private void initRcvDetailVocabulary(int grammarId) {
        detailGrammarAdapterAdapter = new DetailGrammarAdapter(GrammarController.getInstance(dbHelper)
                .findDetailGrammar(grammarId));

        rcvDetailGrammar.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rcvDetailGrammar.setAdapter(detailGrammarAdapterAdapter);
    }
}

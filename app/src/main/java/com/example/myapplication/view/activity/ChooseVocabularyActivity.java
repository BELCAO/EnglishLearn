package com.example.myapplication.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.WindowCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.domain.model.Question;
import com.example.myapplication.domain.model.Vocabulary;
import com.example.myapplication.controller.QuestionController;
import com.example.myapplication.domain.service.database.DatabaseHelper;
import com.example.myapplication.view.adapter.QuestionAdapter;

import java.util.List;

public class ChooseVocabularyActivity extends AppCompatActivity {

    private List<Vocabulary> vocabularyList;
    private RecyclerView recyclerView;
    private QuestionController questionService;
    private QuestionAdapter questionAdapter;
    private Button btnSubmit;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Ensure the content view fits system windows
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        setContentView(R.layout.activity_choose_vocabulary);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        recyclerView = findViewById(R.id.rcv_questions);
        btnSubmit = findViewById(R.id.btn_submit);

        dbHelper = DatabaseHelper.getInstance(this);
        questionService = new QuestionController(dbHelper);

        // Lấy danh sách từ vựng cần luyện trong bài học
        vocabularyList = (List<Vocabulary>) getIntent().getSerializableExtra("list_vocabulary");

        // Tạo danh sách câu hỏi
        List<Question> questions = questionService.generateQuestions(vocabularyList, 10);

        // Hiển thị danh sách câu hỏi bằng RecyclerView
        questionAdapter = new QuestionAdapter(questions);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(questionAdapter);

        // Xử lý sự kiện khi bấm nút "Nộp bài"
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (questionAdapter.getUserAnswers().size() < 10) {
                    Toast.makeText(ChooseVocabularyActivity.this, "Bạn cần trả lời tất cả 10 câu hỏi trước khi nộp bài.", Toast.LENGTH_LONG).show();
                } else {
                    int correctAnswers = questionAdapter.calculateCorrectAnswers();
                    Toast.makeText(ChooseVocabularyActivity.this, "Bạn đã trả lời đúng " + correctAnswers + " câu.", Toast.LENGTH_LONG).show();
                    questionAdapter.showCorrectAnswers();
                }
            }
        });
    }
}

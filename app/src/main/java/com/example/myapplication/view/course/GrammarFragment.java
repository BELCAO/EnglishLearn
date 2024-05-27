package com.example.myapplication.view.course;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.controller.GrammarController;
import com.example.myapplication.controller.VocabularyController;
import com.example.myapplication.domain.model.Grammar;
import com.example.myapplication.domain.model.Vocabulary;
import com.example.myapplication.domain.service.database.DatabaseHelper;
import com.example.myapplication.view.activity.ChooseVocabularyActivity;
import com.example.myapplication.view.adapter.GrammarAdapter;
import com.example.myapplication.view.adapter.VocabularyAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GrammarFragment extends Fragment implements View.OnClickListener {

    private RecyclerView rcvGrammar;
    private GrammarAdapter grammarAdapter;
    private List<Grammar> grammarList;
    private Spinner spnLesson;
    private DatabaseHelper dbHelper;
    private Button btnChooseGrammar;
    private int courseId;
    private final int LIMIT = 10;
    private int size;
    public GrammarFragment(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = DatabaseHelper.getInstance(requireContext());
        findVocabulary(courseId);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_grammar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        spnLesson = view.findViewById(R.id.spn_lesson);
        rcvGrammar = view.findViewById(R.id.rcv_grammar);
        btnChooseGrammar = view.findViewById(R.id.btn_choose_grammar);

        btnChooseGrammar.setOnClickListener(this);

        initSpnLesson();
        initVocabulary();
    }

    /**
     * Ánh xạ các bài học cho khóa học
     * Mỗi bài học sẽ gồm LIMIT từ vựng
     */
    private void initSpnLesson() {
        List<String> lessons = new ArrayList<>();
        double numberOfLesson =  Math.ceil((double) size / LIMIT);

        // Hiển thị selection (Bài 1, Bài 2,...). Mỗi bài có LIMIT từ vựng
        for (int i = 1; i <= numberOfLesson; i++) {
            lessons.add("Bài " + i);
        }
        ArrayAdapter adapter = new ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, lessons);
        spnLesson.setAdapter(adapter);

        // Bắt sự kiện chọn item
        spnLesson.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                /**
                 * offset là giá trị bắt đầu, ví dụ:
                 * Bài 1 -> position = 0 -> offset = 0 * LIMIT = 0
                 * Bài 2 -> position = 1 -> offset = 1 * LIMIT = LIMIT
                 * Lấy ra từ vựng trong khoảng từ offset đến MIN giữa LIMIT và size (tránh danh sách từ vựng không đủ LIMIT)
                 */
                int offset = position * LIMIT;
                grammarAdapter.setGrammarList(grammarList);
                grammarAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * Lấy ra danh sách từ vựng của khóa học từ database dựa vào mã khóa học
     * @param courseId
     */
    private void findVocabulary(int courseId) {
        grammarList = GrammarController.getInstance(dbHelper).find(courseId);

        size = grammarList.size();
    }

    /**
     * Ánh xạ danh sách từ vựng vào recycle view, hiển thị từ 0 đến LIMIT
     */
    private void initVocabulary() {
        grammarAdapter = new GrammarAdapter(grammarList);

        rcvGrammar.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        rcvGrammar.setAdapter(grammarAdapter);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        /**
         * Nếu click "Chọn từ" sẽ chuyển sang màn hình ChooseVocabularyActivity
         * Đồng thời truyền đi danh sách từ vựng cần luyện của bài học
         */
        if(id == R.id.btn_choose_grammar) {
            int offset = spnLesson.getSelectedItemPosition() * LIMIT;
            List<Grammar> targetGrammar = new ArrayList<>(grammarList.subList(offset, Math.min(offset + LIMIT, size)));

            Intent intent = new Intent(requireActivity(), ChooseVocabularyActivity.class);
            intent.putExtra("list_grammar", (Serializable) targetGrammar);
            startActivity(intent);
        }
    }
}

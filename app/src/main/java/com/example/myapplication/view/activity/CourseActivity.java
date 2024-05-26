package com.example.myapplication.view.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.controller.CourseController;
import com.example.myapplication.domain.service.database.DatabaseHelper;
import com.example.myapplication.view.adapter.CourseAdapter;

public class CourseActivity extends AppCompatActivity {

    private RecyclerView rcvCourses;
    private CourseAdapter courseAdapter;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        dbHelper = DatabaseHelper.getInstance(this);

        checkExistDatabase();
        initView();
    }

    /**
     * Kiểm tra nếu tạo database lần đầu thì thêm dữ liệu vào (do sqlite database chỉ lưu riêng biệt trên mỗi thiết bị)
     */
    private void checkExistDatabase() {
        SharedPreferences prefs = getSharedPreferences("cache_data", Context.MODE_PRIVATE);
        boolean isCreatedData = prefs.getBoolean("created_data", false);

        if(!isCreatedData) {
            dbHelper.createData();
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("created_data", true);
            editor.apply();
        }
    }

    /**
     * Ánh xạ view để hiển thị
     */
    private void initView() {
        rcvCourses = findViewById(R.id.rcv_course);
        initCourses();
    }

    /**
     * Lấy dữ liệu khóa học từ db sau đó ánh xạ vào recycle view
     */
    private void initCourses() {
        courseAdapter = new CourseAdapter(CourseController.getInstance(dbHelper).findAll());
        rcvCourses.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rcvCourses.setAdapter(courseAdapter);
    }
}
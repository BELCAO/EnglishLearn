package com.example.myapplication.view.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication.R;
import com.example.myapplication.custom_view.ViewPagerAdapter;
import com.example.myapplication.view.course.GrammarFragment;
import com.example.myapplication.view.course.VocabularyFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class LessonActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        initView();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        tabLayout = findViewById(R.id.tab_layout);
        viewPager2 = findViewById(R.id.viewpager_2);

        initToolbar();
        initTabLayout();
    }

    private void initToolbar() {
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    private void initTabLayout() {
        Intent intent = getIntent();
        int courseId = intent.getIntExtra("course_id", -1);

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new VocabularyFragment(courseId));
        fragmentList.add(new GrammarFragment(courseId));

        viewPager2.setAdapter(new ViewPagerAdapter(this, fragmentList));
        new TabLayoutMediator(this.tabLayout, this.viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            public void onConfigureTab(TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("Từ vựng");
                        break;
                    case 1:
                        tab.setText("Ngữ pháp");
                        break;
                }
            }
        }).attach();
    }
}
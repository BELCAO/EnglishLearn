package com.example.myapplication.view.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.domain.model.Vocabulary;
import com.example.myapplication.view.activity.DetailVocabularyActivity;

import java.util.List;

public class VocabularyAdapter extends RecyclerView.Adapter<VocabularyAdapter.ViewHolder> {

    private List<Vocabulary> vocabularyList;

    public VocabularyAdapter(List<Vocabulary> vocabularyList) {
        this.vocabularyList = vocabularyList;
    }

    public void setVocabularyList(List<Vocabulary> vocabularyList) {
        this.vocabularyList = vocabularyList;
    }

    @NonNull
    @Override
    public VocabularyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.viewholder_vocabulary, parent, false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull VocabularyAdapter.ViewHolder holder, int position) {
        Vocabulary item = vocabularyList.get(position);
        holder.initView(item);
    }

    @Override
    public int getItemCount() {
        return vocabularyList != null? vocabularyList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvWord, tvMean, tvPhonetic;
        Vocabulary vocabulary;
        View viewVocabulary;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvWord = itemView.findViewById(R.id.tv_word);
            tvMean = itemView.findViewById(R.id.tv_mean);
            tvPhonetic = itemView.findViewById(R.id.tv_phonetic);
            viewVocabulary = itemView.findViewById(R.id.vocabulary);

            /**
             * Bắt sự kiện click vào vocabulary sẽ chuyển sang DetailVocabularyActivity
             */
            viewVocabulary.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), DetailVocabularyActivity.class);
                    intent.putExtra("vocabulary", vocabulary);
                    itemView.getContext().startActivity(intent);
                }
            });
        }

        public void initView(Vocabulary vocabulary) {
            this.vocabulary = vocabulary;

            tvWord.setText(vocabulary.getWord());
            tvMean.setText(vocabulary.getMean());
            tvPhonetic.setText(vocabulary.getPhonetic());
        }
    }
}

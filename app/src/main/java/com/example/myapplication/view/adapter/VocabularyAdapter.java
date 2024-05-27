package com.example.myapplication.view.adapter;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.domain.model.Vocabulary;
import com.example.myapplication.view.activity.DetailVocabularyActivity;

import java.util.List;
import java.util.Locale;

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

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvWord, tvMean, tvPhonetic;
        Vocabulary vocabulary;
        View viewVocabulary;
        View imvSpeaker;
        TextToSpeech textToSpeech;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvWord = itemView.findViewById(R.id.tv_word);
            tvMean = itemView.findViewById(R.id.tv_mean);
            tvPhonetic = itemView.findViewById(R.id.tv_phonetic);
            viewVocabulary = itemView.findViewById(R.id.layout_vocabulary);
            imvSpeaker = itemView.findViewById(R.id.speaker);

            viewVocabulary.setOnClickListener(this);
            imvSpeaker.setOnClickListener(this);

            textToSpeech = new TextToSpeech(itemView.getContext(), new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if(status == TextToSpeech.SUCCESS) {
                        textToSpeech.setLanguage(Locale.US);
                    }
                }
            });
        }

        public void initView(Vocabulary vocabulary) {
            this.vocabulary = vocabulary;

            tvWord.setText(vocabulary.getWord());
            tvMean.setText(vocabulary.getMean());
            tvPhonetic.setText(vocabulary.getPhonetic());
        }

        @Override
        public void onClick(View v) {
            int id = v.getId();

            /**
             * Bắt sự kiện click vào vocabulary sẽ chuyển sang DetailVocabularyActivity
             */
            if(id == R.id.layout_vocabulary) {
                Intent intent = new Intent(itemView.getContext(), DetailVocabularyActivity.class);
                intent.putExtra("vocabulary", vocabulary);
                itemView.getContext().startActivity(intent);
            }
            else if(id == R.id.speaker) {
                // Phát âm tiếng Anh khi click vào cái loa
                textToSpeech.speak(vocabulary.getWord(), TextToSpeech.QUEUE_FLUSH, null, null);
            }
        }
    }
}

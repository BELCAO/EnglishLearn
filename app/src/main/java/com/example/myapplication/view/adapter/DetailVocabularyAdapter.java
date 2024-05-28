package com.example.myapplication.view.adapter;

import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.domain.model.DetailVocabulary;

import java.util.List;
import java.util.Locale;

public class DetailVocabularyAdapter extends RecyclerView.Adapter<DetailVocabularyAdapter.ViewHolder> {

    private List<DetailVocabulary> detailVocabularyList;

    public DetailVocabularyAdapter(List<DetailVocabulary> detailVocabularyList) {
        this.detailVocabularyList = detailVocabularyList;
    }

    @NonNull
    @Override
    public DetailVocabularyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.viewholder_detail_vocabulary, parent, false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull DetailVocabularyAdapter.ViewHolder holder, int position) {
        DetailVocabulary item = detailVocabularyList.get(position);
        holder.initView(item);
    }

    @Override
    public int getItemCount() {
        return detailVocabularyList != null? detailVocabularyList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvType, tvWordMean, tvSampleSentence, tvSampleSentenceMean;
        ImageView imvSpeaker;
        TextToSpeech textToSpeech;
        DetailVocabulary detailVocabulary;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvType = itemView.findViewById(R.id.tv_type);
            tvWordMean = itemView.findViewById(R.id.tv_word_mean);
            tvSampleSentence = itemView.findViewById(R.id.tv_sample_sentence);
            tvSampleSentenceMean = itemView.findViewById(R.id.tv_sample_sentence_mean);
            imvSpeaker = itemView.findViewById(R.id.imv_speaker);

            /**
             * Tạo phát âm hệ thống là US
             */
            textToSpeech = new TextToSpeech(itemView.getContext(), status -> {
                if(status == TextToSpeech.SUCCESS) {
                    textToSpeech.setLanguage(Locale.US);
                }
            });

            /**
             * Bắt sự kiện click vào cái loa sẽ đọc câu ví dụ (sampleSentence)
             */
            imvSpeaker.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textToSpeech.speak(detailVocabulary.getSampleSentence(), TextToSpeech.QUEUE_FLUSH, null, null);
                }
            });
        }

        public void initView(DetailVocabulary detailVocabulary) {
            this.detailVocabulary = detailVocabulary;
            tvType.setText(detailVocabulary.getType());
            tvWordMean.setText(detailVocabulary.getWordMean());
            tvSampleSentence.setText(detailVocabulary.getSampleSentence());
            tvSampleSentenceMean.setText(detailVocabulary.getSampleSentenceMean());
        }
    }
}

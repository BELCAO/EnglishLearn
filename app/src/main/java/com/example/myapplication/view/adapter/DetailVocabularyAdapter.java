package com.example.myapplication.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.domain.model.DetailVocabulary;

import java.util.List;

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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvType = itemView.findViewById(R.id.tv_type);
            tvWordMean = itemView.findViewById(R.id.tv_word_mean);
            tvSampleSentence = itemView.findViewById(R.id.tv_sample_sentence);
            tvSampleSentenceMean = itemView.findViewById(R.id.tv_sample_sentence_mean);
        }

        public void initView(DetailVocabulary detailVocabulary) {
            tvType.setText(detailVocabulary.getType());
            tvWordMean.setText(detailVocabulary.getWordMean());
            tvSampleSentence.setText(detailVocabulary.getSampleSentence());
            tvSampleSentenceMean.setText(detailVocabulary.getSampleSentenceMean());
        }
    }
}

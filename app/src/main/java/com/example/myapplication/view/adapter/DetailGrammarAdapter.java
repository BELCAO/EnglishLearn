package com.example.myapplication.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.domain.model.DetailGrammar;
import com.example.myapplication.domain.model.DetailVocabulary;

import java.util.List;

public class DetailGrammarAdapter extends RecyclerView.Adapter<DetailGrammarAdapter.ViewHolder> {


    private List<DetailGrammar> grammardetailList;

    public DetailGrammarAdapter(List<DetailGrammar> grammardetailList){
        this.grammardetailList = grammardetailList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DetailGrammarAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.viewholder_detail_grammar, parent, false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DetailGrammar item = grammardetailList.get(position);
        holder.initView(item);
    }

    @Override
    public int getItemCount() {
        return grammardetailList != null? grammardetailList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvType, tvDescription, tvExample;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvType = itemView.findViewById(R.id.tv_type_grammar);
            tvDescription = itemView.findViewById(R.id.tv_description_grammar);
            tvExample = itemView.findViewById(R.id.tv_example_grammar);

        }

        public void initView(DetailGrammar detailGrammar) {
            tvType.setText(detailGrammar.getId() + "");
            tvExample.setText(detailGrammar.getExample());
            tvDescription.setText(detailGrammar.getDetail());
//            tvSampleSentenceMean.setText(detailVocabulary.getSampleSentenceMean());
        }
    }
}

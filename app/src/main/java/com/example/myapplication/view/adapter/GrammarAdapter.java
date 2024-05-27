package com.example.myapplication.view.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.domain.model.DetailGrammar;
import com.example.myapplication.domain.model.Grammar;



import java.util.List;

public class GrammarAdapter extends RecyclerView.Adapter<GrammarAdapter.ViewHolder> {
    List<Grammar> grammarList;

    public GrammarAdapter(List<Grammar> grammarList){
        this.grammarList = grammarList;
    }

    public void setGrammarList(List<Grammar> grammarList){
        this.grammarList = grammarList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.viewholder_grammar, parent, false
        ));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Grammar item = grammarList.get(position);
        holder.initView(item);
    }

    @Override
    public int getItemCount() {
        return grammarList != null? grammarList.size() : 0;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvRule_name, tvDescription, tvExamle;
        Grammar grammar;
        View viewGrammar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvRule_name = itemView.findViewById(R.id.tv_rule_name);
            tvDescription = itemView.findViewById(R.id.tv_description);
            tvExamle = itemView.findViewById(R.id.tv_example);
            viewGrammar = itemView.findViewById(R.id.grammar);

            /**
             * Bắt sự kiện click vào vocabulary sẽ chuyển sang DetailGrammarActivity
             */
            viewGrammar.setOnClickListener(new View.OnClickListener()  {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), DetailGrammar.class);
                    intent.putExtra("grammar",  grammar);
                    itemView.getContext().startActivity(intent);
                }
            });
        }

        public void initView(Grammar grammar) {
            this.grammar = grammar;

            tvRule_name.setText(grammar.getRule_name());
            tvDescription.setText(grammar.getDescription());
            tvExamle.setText(grammar.getExample());
        }
    }
}

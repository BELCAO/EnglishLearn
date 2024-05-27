package com.example.myapplication.view.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.domain.model.Question;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {

    private List<Question> questionList;
    private Map<Integer, String> userAnswers = new HashMap<>();
    private boolean isSubmitted = false;

    public QuestionAdapter(List<Question> questionList) {
        this.questionList = questionList;
    }

    @NonNull
    @Override
    public QuestionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.viewholder_question, parent, false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionAdapter.ViewHolder holder, int position) {
        Question item = questionList.get(position);
        holder.initView(item, position);
    }

    @Override
    public int getItemCount() {
        return questionList != null ? questionList.size() : 0;
    }

    public int calculateCorrectAnswers() {
        int correctAnswers = 0;
        for (int i = 0; i < questionList.size(); i++) {
            Question question = questionList.get(i);
            String userAnswer = userAnswers.get(i);
            if (question.getCorrectAnswer().equals(userAnswer)) {
                correctAnswers++;
            }
        }
        return correctAnswers;
    }

    public void showCorrectAnswers() {
        isSubmitted = true;
        notifyDataSetChanged();
    }

    public Map<Integer, String> getUserAnswers() {
        return userAnswers;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvWord;
        RadioGroup rgOptions;
        RadioButton rbOption1, rbOption2, rbOption3, rbOption4;
        Question question;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvWord = itemView.findViewById(R.id.tv_word);
            rgOptions = itemView.findViewById(R.id.rg_options);
            rbOption1 = itemView.findViewById(R.id.rb_option1);
            rbOption2 = itemView.findViewById(R.id.rb_option2);
            rbOption3 = itemView.findViewById(R.id.rb_option3);
            rbOption4 = itemView.findViewById(R.id.rb_option4);

            rgOptions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (!isSubmitted) {
                        RadioButton selectedRadioButton = itemView.findViewById(checkedId);
                        if (selectedRadioButton != null) {
                            userAnswers.put(getAdapterPosition(), selectedRadioButton.getText().toString());
                        }
                    }
                }
            });
        }

        public void initView(Question question, int position) {
            this.question = question;

            tvWord.setText(question.getWord());
            List<String> options = question.getOptions();
            rbOption1.setText(options.get(0));
            rbOption2.setText(options.get(1));
            rbOption3.setText(options.get(2));
            rbOption4.setText(options.get(3));

            // Reset màu sắc đáp án khi chưa nộp bài
            rbOption1.setTextColor(Color.BLACK);
            rbOption2.setTextColor(Color.BLACK);
            rbOption3.setTextColor(Color.BLACK);
            rbOption4.setTextColor(Color.BLACK);

            rgOptions.setOnCheckedChangeListener(null); // clear listener to prevent infinite loop
            rgOptions.clearCheck(); // clear previous selections

            if (isSubmitted) {
                String userAnswer = userAnswers.get(position);
                if (userAnswer != null) {
                    if (question.getCorrectAnswer().equals(userAnswer)) {
                        findRadioButtonByText(userAnswer).setTextColor(Color.GREEN);
                    } else {
                        findRadioButtonByText(userAnswer).setTextColor(Color.RED);
                        findRadioButtonByText(question.getCorrectAnswer()).setTextColor(Color.GREEN);
                    }
                } else {
                    findRadioButtonByText(question.getCorrectAnswer()).setTextColor(Color.GREEN);
                }
                rgOptions.setEnabled(false);
                for (int i = 0; i < rgOptions.getChildCount(); i++) {
                    rgOptions.getChildAt(i).setEnabled(false);
                }
            } else {
                String userAnswer = userAnswers.get(position);
                if (userAnswer != null) {
                    RadioButton selectedRadioButton = findRadioButtonByText(userAnswer);
                    if (selectedRadioButton != null) {
                        selectedRadioButton.setChecked(true);
                    }
                }
                rgOptions.setEnabled(true);
                for (int i = 0; i < rgOptions.getChildCount(); i++) {
                    rgOptions.getChildAt(i).setEnabled(true);
                }
            }

            rgOptions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (!isSubmitted) {
                        RadioButton selectedRadioButton = itemView.findViewById(checkedId);
                        if (selectedRadioButton != null) {
                            userAnswers.put(getAdapterPosition(), selectedRadioButton.getText().toString());
                        }
                    }
                }
            });
        }

        private RadioButton findRadioButtonByText(String text) {
            if (rbOption1.getText().toString().equals(text)) {
                return rbOption1;
            } else if (rbOption2.getText().toString().equals(text)) {
                return rbOption2;
            } else if (rbOption3.getText().toString().equals(text)) {
                return rbOption3;
            } else if (rbOption4.getText().toString().equals(text)) {
                return rbOption4;
            } else {
                return null;
            }
        }
    }
}

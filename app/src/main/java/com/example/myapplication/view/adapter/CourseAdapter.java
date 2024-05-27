package com.example.myapplication.view.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.NumberFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.domain.model.Course;
import com.example.myapplication.view.activity.LessonActivity;

import java.util.List;
import java.util.Locale;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {

    private List<Course> courses;

    /**
     * Lấy Courses List
     * @param courses
     */
    public CourseAdapter(List<Course> courses) {
        this.courses = courses;
    }

    /**
     * Tạo ViewHolder
     *@return Chuyển layout -> ViewHolder
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewholder_course, parent, false));
    }

    /**
     * Gán mỗi khóa học vào viewHolder
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Course item = courses.get(position);
        holder.initView(item);
    }

    /**
     *
     * @return Số lượng khóa học trong list
     */
    @Override
    public int getItemCount() {
        return courses != null? courses.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvCourseName, tvCoursePrice;
        Button btnRegister;
        private Course course;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCourseName = itemView.findViewById(R.id.tv_course_name);
            tvCoursePrice = itemView.findViewById(R.id.tv_course_price);
            btnRegister = itemView.findViewById(R.id.btn_register);

            btnRegister.setOnClickListener(this);
        }

        public void initView(Course course) {
            this.course = course;

            tvCourseName.setText(course.getName());
            int price = course.getPrice();
            /**
             * Nếu khóa học có tính phí thì hiển thị giá tiền + nút đăng ký
             * Nếu đã đăng ký (hoặc free) thì sẽ là nút "Học ngay"
             */
            if(price > 0) {
                NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
                tvCoursePrice.setText(currencyFormat.format(price));
                btnRegister.setText("Đăng ký");
            }
            else {
                tvCoursePrice.setText("");
                btnRegister.setText("Học ngay");
                btnRegister.setBackgroundColor(itemView.getResources().getColor(R.color.primary));
            }
        }

        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
            builder.setMessage("Xác nhận đăng ký khóa học với " + course.getPrice() + " VND");
            builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Xử lý thanh toán và hiển thị thông báo thanh toán thành công
                    Toast.makeText(itemView.getContext(), "Thanh toán thành công", Toast.LENGTH_SHORT).show();
                    // Chuyển nút Đăng ký thành nút Học ngay
                    tvCoursePrice.setText("");
                    btnRegister.setText("Học ngay");
                    btnRegister.setBackgroundColor(itemView.getResources().getColor(R.color.primary));
                    // Chuyển sang lesson activity
                    Intent intent = new Intent(itemView.getContext(), LessonActivity.class);
                    intent.putExtra("course_id", course.getId());
                    itemView.getContext().startActivity(intent);
                }
            });
            builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.show();
        }

    }
}

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
import com.example.myapplication.domain.service.database.DatabaseHelper;
import com.example.myapplication.view.activity.LessonActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {

    private List<Course> courses;
    private static Map<Integer, Boolean> enrolledCoursesMap = new HashMap<>();
    /**
     * Lấy Courses List
     *
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
        holder.initView(item, enrolledCoursesMap);
    }

    /**
     *
     * @return Số lượng khóa học trong list
     */
    @Override
    public int getItemCount() {
        return courses != null? courses.size() : 0;
    }

    public void setEnrolledCoursesMap(Map<Integer, Boolean> enrolledCoursesMap) {
        this.enrolledCoursesMap = enrolledCoursesMap;
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvCourseName;
        public TextView tvCoursePrice;
        Button btnRegister;
        private Course course;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCourseName = itemView.findViewById(R.id.tv_course_name);
            tvCoursePrice = itemView.findViewById(R.id.tv_course_price);
            btnRegister = itemView.findViewById(R.id.btn_register);

            btnRegister.setOnClickListener(this);
        }

        /**
         * Nếu khóa học có tính phí thì hiển thị giá tiền + nút đăng ký
         * Nếu đã đăng ký (hoặc free) thì sẽ là nút "Học ngay"
         * 1.Hiển thị danh sách khóa học gồm Tên và giá tiền
         * @param course
         * @param enrolledCoursesMap
         */
        public void initView(Course course, Map<Integer, Boolean> enrolledCoursesMap) {
            this.course = course;
            tvCourseName.setText(course.getName());
            int price = course.getPrice();
            boolean isEnrolled = enrolledCoursesMap.containsKey(course.getId()) && enrolledCoursesMap.get(course.getId());

            if (isEnrolled || price ==0) {
                enrolledCoursesMap.put(course.getId(), true);
                tvCoursePrice.setText("");
                btnRegister.setText("Học ngay");
                btnRegister.setBackgroundColor(itemView.getResources().getColor(R.color.primary));
            } else if (price > 0) {
                NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
                tvCoursePrice.setText(currencyFormat.format(price));
                btnRegister.setText("Đăng ký");
            }
        }

        /**
         *  Nếu khóa học miễn phí, người dùng có thể đăng ký mà không cần xác nhận
         *  2. Bấm đăng ký
         *  3. Hiển thị hộp thoại xác nhận thanh toán (đăng ký)
         */
        @Override
        public void onClick(View v) {
            boolean isEnrolled = enrolledCoursesMap.containsKey(course.getId()) && enrolledCoursesMap.get(course.getId());
            if(!isEnrolled){
                showConfirmationDialog();
            }
            else{
                Intent intent = new Intent(itemView.getContext(), LessonActivity.class);
                intent.putExtra("course_id", course.getId());
                itemView.getContext().startActivity(intent);
            }

        }

        private void showConfirmationDialog() {
            AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
            builder.setMessage("Xác nhận đăng ký khóa học với " + course.getPrice() + " VND");
            builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Xử lý đăng ký
                    enrollCourse();
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

        private void enrollCourse() {
            /**
             * cập nhật trạng thái đăng ký trong csdl, cập nhật nút đăng ký
             * 4. Xác nhận đăng ký
             * 5. Thông báo đăng ký thành công.
              */
            enrolledCoursesMap.put(course.getId(), true);
            Toast.makeText(itemView.getContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
            tvCoursePrice.setText("");
            btnRegister.setText("Học ngay");
            btnRegister.setBackgroundColor(itemView.getResources().getColor(R.color.primary));
        }


    }
}

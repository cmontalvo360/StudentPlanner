package android.cesarplanner.studentplanner.controllers;

import android.cesarplanner.studentplanner.R;
import android.cesarplanner.studentplanner.entities.Course;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder>{
    class CourseViewHolder extends RecyclerView.ViewHolder {
        private final TextView courseItemViewTitle;
        private final TextView courseItemViewStart;
        private CourseViewHolder(View itemView) {
            super(itemView);
            courseItemViewTitle = itemView.findViewById(R.id.textViewCourseTitle);
            courseItemViewStart = itemView.findViewById(R.id.textViewCourseStart);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final Course current = courses.get(position);
                    Intent intent = new Intent(context, CourseDetails.class);
                    intent.putExtra("id", current.getTermID());
                    intent.putExtra("courseTitle", current.getCourseTitle());
                    intent.putExtra("start", current.getStartDate());
                    intent.putExtra("end", current.getEndDate());
                    context.startActivity(intent);
                }
            });
        }
    }

    private List<Course> courses;
    private final Context context;
    private final LayoutInflater inFlater;
    public CourseAdapter(Context context) {
        inFlater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public CourseAdapter.CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inFlater.inflate(R.layout.course_list_item, parent, false);

        return new CourseAdapter.CourseViewHolder((itemView));
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.CourseViewHolder holder, int position) {
        if(courses != null) {
            Course current = courses.get(position);
            String title = current.getCourseTitle();
            String start = current.getStartDate();
            holder.courseItemViewTitle.setText(title);
            holder.courseItemViewStart.setText(start);

        } else {
            holder.courseItemViewTitle.setText("No Courses Associated");
        }
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
        notifyDataSetChanged();
    }
}

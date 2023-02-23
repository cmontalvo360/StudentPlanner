package android.cesarplanner.studentplanner.controllers;

import android.cesarplanner.studentplanner.R;
import android.cesarplanner.studentplanner.entities.Assessment;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.AssessmentViewHolder> {
    class AssessmentViewHolder extends RecyclerView.ViewHolder {
        private final TextView assessmentItemViewTitle;
        private final TextView assessmentItemViewType;
        private final TextView assessmentItemViewStart;
        private AssessmentViewHolder(View itemView) {
            super(itemView);
            assessmentItemViewTitle = itemView.findViewById(R.id.textViewTitle);
            assessmentItemViewType = itemView.findViewById(R.id.textViewType);
            assessmentItemViewStart = itemView.findViewById(R.id.textViewStart);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final Assessment current = assessments.get(position);
                    Intent intent = new Intent(context, AssessmentDetails.class);
                    intent.putExtra("id", current.getAssessmentID());
                    intent.putExtra("title", current.getTitle());
                    intent.putExtra("type", current.getType());
                    intent.putExtra("start", current.getStartDate());
                    intent.putExtra("end", current.getEndDate());
                    intent.putExtra("courseID", current.getCourseID());
                    context.startActivity(intent);
                }
            });
        }
    }

    private List<Assessment> assessments;
    private final Context context;
    private final LayoutInflater inFlater;
    public AssessmentAdapter(Context context) {
        inFlater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public AssessmentAdapter.AssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inFlater.inflate(R.layout.assessment_list_item, parent, false);

        return new AssessmentViewHolder((itemView));
    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentAdapter.AssessmentViewHolder holder, int position) {
        if(assessments != null) {
            Assessment current = assessments.get(position);
            String title = current.getTitle();
            String type = current.getType();
            String start = current.getStartDate();
            holder.assessmentItemViewTitle.setText(title);
            holder.assessmentItemViewType.setText(type);
            holder.assessmentItemViewStart.setText(start);

        } else {
            holder.assessmentItemViewTitle.setText("No Assessments Associated");
        }
    }

    @Override
    public int getItemCount() {
        return assessments.size();
    }

    public void setAssessments(List<Assessment> assessments) {
        this.assessments = assessments;
        notifyDataSetChanged();
    }
}

package com.example.mockitodemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mockitodemo.model.Subject;

import java.util.ArrayList;
import java.util.List;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectHolder> {
    private List<Subject> subjects = new ArrayList<>();
    private onItemClickListner listner;

    @NonNull
    @Override
    public SubjectHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subject_item, parent, false);
        return new SubjectHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectHolder holder, int position) {
        Subject subject = subjects.get(position);
        holder.title.setText(subject.getTitle());
        holder.disc.setText(subject.getDisc());

    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
        notifyDataSetChanged();
    }

    public Subject getSujectPosition(int pos) {
        return subjects.get(pos);
    }

    class SubjectHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView disc;

        public SubjectHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_title);
            disc = itemView.findViewById(R.id.text_desc);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (listner != null && pos != RecyclerView.NO_POSITION)
                        listner.onClick(subjects.get(pos));
                }
            });

        }
    }

    public interface onItemClickListner {
        void onClick(Subject subject);
    }

    public void setOnItemClickListner(onItemClickListner listner) {
        this.listner = listner;
    }
}

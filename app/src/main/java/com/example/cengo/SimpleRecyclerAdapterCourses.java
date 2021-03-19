package com.example.cengo;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SimpleRecyclerAdapterCourses extends RecyclerView.Adapter<SimpleRecyclerAdapter.ViewHolder>{

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView homework_name;
        public TextView lesson_name;
        public TextView due_date;
        public ImageView lesson_img;
        public CardView card_view;


        public ViewHolder(View view) {
            super(view);

            card_view = (CardView)view.findViewById(R.id.card_view);
            homework_name = (TextView)view.findViewById(R.id.homework_name);
            lesson_name = (TextView)view.findViewById(R.id.lesson_name);
            lesson_img = (ImageView)view.findViewById(R.id.lesson_photo);
            due_date = (TextView)view.findViewById(R.id.due_date);

        }
    }

    List<Course> courseList;
    private CustomItemClickListener listener;
    public SimpleRecyclerAdapterCourses(List<Course>course_list, CustomItemClickListener listener) {
        this.courseList=course_list;
        this.listener=listener;
    }

    @NonNull
    @Override
    public SimpleRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_layout, parent, false);
        final SimpleRecyclerAdapter.ViewHolder view_holder = new SimpleRecyclerAdapter.ViewHolder(v);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, view_holder.getPosition());
            }
        });

        return view_holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleRecyclerAdapter.ViewHolder holder, int position) {
        holder.homework_name.setText(courseList.get(position).getCourseName());
        holder.lesson_name.setText(courseList.get(position).getTeacherName());
        holder.lesson_img.setImageResource(R.drawable.courses_photo);
    }


    @Override
    public int getItemCount() {
       return courseList.size();
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}

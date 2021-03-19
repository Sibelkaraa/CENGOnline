package com.example.cengo;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SimpleRecyclerAdapter extends RecyclerView.Adapter<SimpleRecyclerAdapter.ViewHolder> {

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

    List<Homework> listHomework;
    CustomItemClickListener listener;
    public SimpleRecyclerAdapter(List<Homework> list_homework, CustomItemClickListener listener) {
        this.listHomework = list_homework;
        this.listener = listener;
    }



    @Override
    public SimpleRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_layout, parent, false);
        final ViewHolder view_holder = new ViewHolder(v);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, view_holder.getPosition());
            }
        });

        return view_holder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.homework_name.setText(listHomework.get(position).getHw_name());
        holder.lesson_name.setText(listHomework.get(position).getLesson_name());
        holder.lesson_img.setImageResource(listHomework.get(position).getPhoto_id());
        holder.due_date.setText("Due date : "+listHomework.get(position).getDue_date());

    }

    @Override
    public int getItemCount() {
        return listHomework.size();
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


}

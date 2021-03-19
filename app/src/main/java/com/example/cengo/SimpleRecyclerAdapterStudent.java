package com.example.cengo;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SimpleRecyclerAdapterStudent extends RecyclerView.Adapter<SimpleRecyclerAdapterStudent.ViewHolder>{


    Context context;
    Database db;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        CardView card_view;
        TextView teacher_name;
        TextView post;
        TextView comment;
        ImageButton send;
        TextView comment_text;
        ImageView person_img;


        public ViewHolder(View view) {
            super(view);
            card_view = (CardView)view.findViewById(R.id.card_view13);
            teacher_name = view.findViewById(R.id.teacher_name13);
            post = view.findViewById(R.id.post13);
            person_img = view.findViewById(R.id.person_photo13);
            comment=view.findViewById(R.id.comment13);
            comment_text=view.findViewById(R.id.comment_text13);
            send=view.findViewById(R.id.send13);
        }
    }

    List<Post> PostList;
    private CustomItemClickListener listener;

    public SimpleRecyclerAdapterStudent(List<Post> postList, CustomItemClickListener listener) {
        PostList = postList;
        this.listener = listener;

    }


    @Override
    public SimpleRecyclerAdapterStudent.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        context=parent.getContext();
        db = new Database(context);
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_student_stream, parent, false);
        final SimpleRecyclerAdapterStudent.ViewHolder view_holder = new SimpleRecyclerAdapterStudent.ViewHolder(v);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, view_holder.getPosition());
            }
        });

        return view_holder;
    }


    List<Comment> comment= new ArrayList<Comment>();
    String comments = "";
    @Override
    public void onBindViewHolder(final SimpleRecyclerAdapterStudent.ViewHolder holder, final int position) {


        comments ="";
        holder.teacher_name.setText(PostList.get(position).getTeacher_name());
        holder.post.setText(PostList.get(position).getPost());
        holder.person_img.setImageResource(R.drawable.user);
        if(!db.DataList_Comment(PostList.get(position).getPost()).isEmpty()){
            comment = db.DataList_Comment(PostList.get(position).getPost());
            for (Comment c : comment) {
                comments += c.getPerson_name()+" : "+c.getComment()+"\n";
            }
            holder.comment.setText(comments);
        }
        //CommentAdapter commentAdapter = new CommentAdapter((ArrayList<Comment>) comment,context);
        //holder.list.setAdapter(commentAdapter);
        holder.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!holder.comment_text.getText().toString().trim().equals("")) {
                    db.Add_comment(PostList.get(position).getPost(), MainActivity.person_name +" "+MainActivity.person_surname, holder.comment_text.getText().toString());
                    comment = db.DataList_Comment(PostList.get(position).getPost());
                    for (Comment c : comment) {
                        comments += c.getPerson_name()+" : "+c.getComment()+"\n";
                    }
                    holder.comment.setText(comments);
                    holder.comment_text.setText("");
                    notifyDataSetChanged();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return PostList.size();
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}

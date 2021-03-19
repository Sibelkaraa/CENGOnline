package com.example.cengo;


import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SimpleRecyclerAdapterStream extends RecyclerView.Adapter<SimpleRecyclerAdapterStream.ViewHolder> {


    Context context;
    Database db;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        CardView card_view;
        TextView teacher_name;
        TextView post;
        ImageView txtOptionDigit;
        TextView comment;
        ImageButton send;
        TextView comment_text;
        ImageView person_img;


        public ViewHolder(View view) {
            super(view);
            card_view = (CardView)view.findViewById(R.id.card_view2);
            teacher_name = view.findViewById(R.id.teacher_name);
            post = view.findViewById(R.id.post);
            person_img = view.findViewById(R.id.person_photo);
            txtOptionDigit=view.findViewById(R.id.ib_popup_menu);
            comment=view.findViewById(R.id.comment);
            comment_text=view.findViewById(R.id.comment_text);
            send=view.findViewById(R.id.send);
        }
    }

    List<Post> PostList;
    private CustomItemClickListener listener;

    public SimpleRecyclerAdapterStream(List<Post> postList, CustomItemClickListener listener) {
        PostList = postList;
        this.listener = listener;

    }


    @Override
    public SimpleRecyclerAdapterStream.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        context=parent.getContext();
        db = new Database(context);
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_stream_layout, parent, false);
        final ViewHolder view_holder = new ViewHolder(v);

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
    public void onBindViewHolder(final ViewHolder holder, final int position) {


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
        holder.txtOptionDigit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context,holder.txtOptionDigit);
                popupMenu.inflate(R.menu.popup_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_edit:
                                if((MainActivity.person_name+" "+MainActivity.person_surname).equals(PostList.get(position).getTeacher_name())){
                                    final Dialog dialog = new Dialog(context);
                                    dialog.setContentView(R.layout.custom_dialog_edit);

                                    final Button cancel2 = (Button) dialog.findViewById(R.id.cancel2);
                                    Button addPost2 = (Button) dialog.findViewById(R.id.add_post2);
                                    TextView teacherName2 = (TextView) dialog.findViewById(R.id.Teacher_name2);
                                    final EditText newPost2 = (EditText) dialog.findViewById(R.id.new_post2);

                                    teacherName2.setText(MainActivity.person_name+" "+MainActivity.person_surname);

                                    addPost2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            if(!newPost2.getText().toString().trim().equals("")){
                                                db.UpdatePost(PostList.get(position).getPost(),newPost2.getText().toString());
                                                notifyDataSetChanged();
                                                List<Post> temp = new ArrayList<Post>();
                                                for(Post p: db.DataList_Post()){
                                                    if(p.getCourse_name().equals(CoursesTeacher.course_name)){
                                                        temp.add(p);
                                                    }
                                                }
                                                PostList=temp;
                                                dialog.dismiss();
                                                notifyDataSetChanged();
                                            }
                                        }
                                    });

                                    cancel2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            dialog.dismiss();
                                        }
                                    });

                                    dialog.show();
                                }
                                else{
                                    Toast.makeText(context,"This post is not yours. You can not edit!",Toast.LENGTH_LONG).show();
                                }
                                break;
                            case R.id.menu_delete:
                                if((MainActivity.person_name+" "+MainActivity.person_surname).equals(PostList.get(position).getTeacher_name())) {
                                    db.DeletePost(CoursesTeacher.course_name, PostList.get(position).getPost());
                                    PostList.remove(position);
                                    notifyDataSetChanged();
                                }
                                else{
                                    Toast.makeText(context,"This post is not yours. You can not delete!",Toast.LENGTH_LONG).show();
                                }
                                break;
                            default:
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
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

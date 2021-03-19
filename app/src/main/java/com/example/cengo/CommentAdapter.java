package com.example.cengo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CommentAdapter extends BaseAdapter {

    private ArrayList<Comment> Comment;
    private Context context;
    private LayoutInflater layoutInflater;

    public CommentAdapter(ArrayList<Comment> comment, Context context) {
        this.Comment = comment;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View customView = layoutInflater.inflate(R.layout.comments_card_view,null);
        ImageView photo = (ImageView) customView.findViewById(R.id.imageView7);
        TextView name = (TextView) customView.findViewById(R.id.name7);
        TextView comment = (TextView) customView.findViewById(R.id.commentt);

        photo.setImageResource(R.drawable.sari);
        name.setText(Comment.get(i).getPerson_name());
        comment.setText(Comment.get(i).getComment());

        return customView;
    }
}

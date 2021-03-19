package com.example.cengo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class StreamStudent extends AppCompatActivity {

    Database db = new Database(this);
    RecyclerView recycler_view;
    List<Post> listPost = new ArrayList<Post>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stream_student);

        recycler_view = (RecyclerView) findViewById(R.id.recyclerView13);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recycler_view.setLayoutManager(layoutManager);


        if (!db.DataList_Post().isEmpty()) {
            for (Post p : db.DataList_Post()) {
                if (p.getCourse_name().equals(CoursesStudent.course_name)) {
                    listPost.add(p);
                }
            }
            SimpleRecyclerAdapterStudent adapter_items = new SimpleRecyclerAdapterStudent(listPost, new CustomItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {

                }
            });

            recycler_view.setHasFixedSize(true);

            recycler_view.setAdapter(adapter_items);

            recycler_view.setItemAnimator(new DefaultItemAnimator());

        }
    }
}

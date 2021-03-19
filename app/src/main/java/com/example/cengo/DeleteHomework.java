package com.example.cengo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class DeleteHomework extends AppCompatActivity {

    Button delete;
    private RecyclerView recycler_view;
    private List<Homework> homeworkList;
    Database db = new Database(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_homework);
        delete=(Button)findViewById(R.id.edit);

        recycler_view=(RecyclerView)findViewById(R.id.recycler_view1);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recycler_view.setLayoutManager(layoutManager);

        if(!db.DataList_Homework().isEmpty()){
            homeworkList = db.DataList_Homework();
            SimpleRecyclerAdapter adapter_items = new SimpleRecyclerAdapter(homeworkList, new CustomItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    final Homework homework = homeworkList.get(position);
                    delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            db.DeleteHomework(homework.getHw_name());
                            Intent i = new Intent(getApplicationContext(),AssignmentTeacher.class);
                            startActivity(i);
                        }
                    });

                }
            });
            recycler_view.setHasFixedSize(true);

            recycler_view.setAdapter(adapter_items);

            recycler_view.setItemAnimator(new DefaultItemAnimator());

        }

    }

}

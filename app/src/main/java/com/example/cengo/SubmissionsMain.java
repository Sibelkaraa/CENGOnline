package com.example.cengo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class SubmissionsMain extends AppCompatActivity implements CustomItemClickListener{

    private RecyclerView recycler_view;
    private List<Homework> homeworkList;
    static Homework hw;
    Database db = new Database(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submissions_main);

        recycler_view = (RecyclerView)findViewById(R.id.recycler_view3);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recycler_view.setLayoutManager(layoutManager);


        if(!db.DataList_Homework().isEmpty()){
            homeworkList = db.DataList_Homework();
            SimpleRecyclerAdapter adapter_items = new SimpleRecyclerAdapter(homeworkList, new CustomItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    hw = homeworkList.get(position);
                    Intent i = new Intent(getApplicationContext(),SubmissionsList.class);
                    startActivity(i);
                }
            });
            recycler_view.setHasFixedSize(true);
            recycler_view.setAdapter(adapter_items);
            recycler_view.setItemAnimator(new DefaultItemAnimator());
        }
    }
    @Override
    public void onItemClick(View v, int position) {

    }
}

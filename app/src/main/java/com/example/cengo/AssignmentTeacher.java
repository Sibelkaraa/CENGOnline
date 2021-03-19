package com.example.cengo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AssignmentTeacher extends AppCompatActivity {

    private RecyclerView recycler_view;
    private List<Homework> homeworkList;
    Button add, edit, delete;
    Database db = new Database(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_teacher);
        recycler_view = (RecyclerView)findViewById(R.id.recycler_view);
        add=(Button)findViewById(R.id.add7) ;
        edit=(Button)findViewById(R.id.edit7);
        delete=(Button)findViewById(R.id.delete7) ;

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recycler_view.setLayoutManager(layoutManager);



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),AddHomework.class);
                startActivity(i);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),DeleteHomework.class);
                startActivity(i);
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),EditHomework.class);
                startActivity(i);
            }
        });

        if(!db.DataList_Homework().isEmpty()){
            homeworkList = db.DataList_Homework();
            SimpleRecyclerAdapter adapter_items = new SimpleRecyclerAdapter(homeworkList, new CustomItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    Log.d("position", "Tıklanan Pozisyon:" + position);
                    Homework homework = homeworkList.get(position);
                    Toast.makeText(getApplicationContext(),"pozisyon:"+" "+position+" "+"Ad:"+homework.getHw_name(), Toast.LENGTH_SHORT).show();
                }
            });
            recycler_view.setHasFixedSize(true);

            recycler_view.setAdapter(adapter_items);

            recycler_view.setItemAnimator(new DefaultItemAnimator());
        }

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(MainActivity.person_type.equals("teacher")){
            Intent i = new Intent(getApplicationContext(),MainPageTeacher.class);
            startActivity(i);
        }
        else{
            Intent i = new Intent(getApplicationContext(),MainPageTeacher.class);
            startActivity(i);
        }
    }
}

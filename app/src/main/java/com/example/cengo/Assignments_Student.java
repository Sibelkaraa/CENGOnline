package com.example.cengo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Assignments_Student extends AppCompatActivity implements CustomItemClickListener {

    private RecyclerView recycler_view;
    private List<Homework> homeworkList;
    Database db = new Database(this);
    static String lesson_name;
    static String homework_name;
    static String explanation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignments__student);
        recycler_view = (RecyclerView)findViewById(R.id.recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recycler_view.setLayoutManager(layoutManager);


        if(!db.DataList_Homework().isEmpty()){
            homeworkList = db.DataList_Homework();
            SimpleRecyclerAdapter adapter= new SimpleRecyclerAdapter(homeworkList, new CustomItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    final Homework homework = homeworkList.get(position);
                    AlertDialog.Builder builder = new AlertDialog.Builder(Assignments_Student.this,R.style.MyDialogTheme);

                    builder.setTitle("Homework: "+homework.getHw_name());
                    builder.setMessage("Due date: "+homework.getDue_date());
                    builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    });
                    builder.setPositiveButton("UPLOAD", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent i = new Intent(getApplicationContext(),HomeworkUpload.class);
                            startActivity(i);
                            lesson_name=homework.getLesson_name();
                            homework_name=homework.getHw_name();
                            explanation=homework.getExplanation();

                        }
                    });
                    builder.show();
                }
            });
            recycler_view.setHasFixedSize(true);

            recycler_view.setAdapter(adapter);

            recycler_view.setItemAnimator(new DefaultItemAnimator());
        }
    }

    @Override
    public void onItemClick(View v, int position) {
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(MainActivity.person_type.equals("teacher")){
            Intent i = new Intent(getApplicationContext(),MainPageTeacher.class);
            startActivity(i);
        }
        else{
            Intent i = new Intent(getApplicationContext(),MainPageStudent.class);
            startActivity(i);
        }
    }
}


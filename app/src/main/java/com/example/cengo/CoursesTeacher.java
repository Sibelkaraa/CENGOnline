package com.example.cengo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CoursesTeacher extends AppCompatActivity {

    static String course_name;
    Button add_course, edit_course, delete_course;
    RecyclerView recycler_view;
    Database db= new Database(this);
    List<Course> listCourses = new ArrayList<Course>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_teacher);

        add_course=(Button)findViewById(R.id.add);
        edit_course=(Button)findViewById(R.id.edit);
        delete_course=(Button)findViewById(R.id.delete);
        recycler_view = (RecyclerView)findViewById(R.id.recycler_view5);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recycler_view.setLayoutManager(layoutManager);

        if(!db.DataList_Course().isEmpty()){
            listCourses = db.DataList_Course();
            SimpleRecyclerAdapterCourses adapter_items = new SimpleRecyclerAdapterCourses(listCourses, new CustomItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    Intent i = new Intent(getApplicationContext(),StreamTeacher.class);
                    startActivity(i);
                    course_name=listCourses.get(position).getCourseName();



                }
            });
            recycler_view.setHasFixedSize(true);

            recycler_view.setAdapter(adapter_items);

            recycler_view.setItemAnimator(new DefaultItemAnimator());

        }

        add_course.setOnClickListener(new View.OnClickListener() {///kurs ekleme sayfası
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getApplicationContext(),AddCourse.class);
                startActivity(i);
            }});

        edit_course.setOnClickListener(new View.OnClickListener() {///edit kurs sayfası
            @Override
            public void onClick(View v) {
                if(db.DataList_Course().isEmpty()){
                    Toast toast = Toast.makeText(getApplicationContext(), "You must add a course first!", Toast.LENGTH_LONG);
                    toast.show();
                }
                else{
                    Intent i = new Intent(getApplicationContext(),EditCourse.class);
                    startActivity(i);
                }
            }
        });

        delete_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(db.DataList_Course().isEmpty()){
                    Toast toast = Toast.makeText(getApplicationContext(), "There are no registered courses to delete", Toast.LENGTH_LONG);
                    toast.show();
                }
                else {
                    Intent i = new Intent(getApplicationContext(), DeleteCourse.class);
                    startActivity(i);
                }
            }
        });
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
                                       
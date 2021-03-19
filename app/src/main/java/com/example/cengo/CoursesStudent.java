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
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CoursesStudent extends AppCompatActivity implements CustomItemClickListener{

    ListView list;
    static String course_name;
    Database db= new Database(this);
    private RecyclerView recycler_view;
    private List<Course> courseList;
    List<Course> listCourses = new ArrayList<Course>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_student);
        recycler_view = (RecyclerView)findViewById(R.id.recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recycler_view.setLayoutManager(layoutManager);


        if(!db.DataList_Course().isEmpty()){
            listCourses = db.DataList_Course();
            SimpleRecyclerAdapterCourses adapter_items = new SimpleRecyclerAdapterCourses(listCourses, new CustomItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    Intent i = new Intent(getApplicationContext(),StreamStudent.class);
                    startActivity(i);
                    course_name=listCourses.get(position).getCourseName();
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

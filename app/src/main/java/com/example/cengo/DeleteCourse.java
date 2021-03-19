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
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class DeleteCourse extends AppCompatActivity {

    Database db=new Database(this);
    List<Course> listCourses = new ArrayList<Course>();
    ListView listview;
    Button delete;
    RecyclerView recycler_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_course);

        delete=(Button)findViewById(R.id.deletebutton);
        recycler_view=(RecyclerView)findViewById(R.id.recycler_view7);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recycler_view.setLayoutManager(layoutManager);

        if(!db.DataList_Course().isEmpty()) {
            listCourses = db.DataList_Course();
            SimpleRecyclerAdapterCourses adapter_items = new SimpleRecyclerAdapterCourses(listCourses, new CustomItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    final Course course = listCourses.get(position);
                    delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            db.DeleteCourse(course.getCourseName());
                            Intent i = new Intent(getApplicationContext(), CoursesTeacher.class);
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

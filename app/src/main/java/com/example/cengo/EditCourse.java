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
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.List;

public class EditCourse extends AppCompatActivity {

    Spinner spinnert;
    EditText rename_course;
    RecyclerView recycler_view;
    String course_name, teacher_name, oldcoursename;
    Button edit;
    Database db=new Database(this);
    List<Course> listCourses = new ArrayList<Course>();
    List<String> teacher_names= new ArrayList<String>();
    List<String> courses_names= new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course);

        edit = (Button) findViewById(R.id.edit);
        recycler_view = (RecyclerView) findViewById(R.id.recycler_view8);
        rename_course=(EditText) findViewById(R.id.spinnercourse);
        spinnert=(Spinner)findViewById(R.id.spinnerteacher);

        List<Teacher> teacherList=db.DataList_Teacher();
        for (Teacher t : teacherList){
            teacher_names.add(t.getName()+" "+t.getSurname());
        }
        listCourses=db.DataList_Course();
        for (Course c : listCourses){
            courses_names.add(c.getCourseName());
        }


        ArrayAdapter adaptert = new ArrayAdapter(this,android.R.layout.simple_spinner_item,teacher_names);
        adaptert.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnert.setAdapter(adaptert);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recycler_view.setLayoutManager(layoutManager);

        if (!db.DataList_Course().isEmpty()) {
            listCourses = db.DataList_Course();
            SimpleRecyclerAdapterCourses adapter_items = new SimpleRecyclerAdapterCourses(listCourses, new CustomItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    final Course course = listCourses.get(position);
                    oldcoursename=course.getCourseName();
                    edit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            course_name = rename_course.getText().toString();
                            teacher_name = String.valueOf(spinnert.getSelectedItem());
                            db.UpdateCourse(oldcoursename, teacher_name, course_name);
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

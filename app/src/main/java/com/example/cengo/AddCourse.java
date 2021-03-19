package com.example.cengo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddCourse extends AppCompatActivity {
    Button addCourse;
    String course_name, teacher_name;
    Spinner spinner;
    Database db=new Database(this);
    List<String> teacher_names= new ArrayList<String>();
    List<Course> courseList=new ArrayList<Course>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        addCourse=(Button)findViewById(R.id.addcourse);
        spinner=(Spinner)findViewById(R.id.spinner3);

        List<Teacher> teacherList=db.DataList_Teacher();
        for (Teacher t : teacherList){
            teacher_names.add(t.getName()+" "+t.getSurname());
        }
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,teacher_names);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                course_name=((EditText)findViewById(R.id.answecoursename2)).getText().toString();
                teacher_name=String.valueOf(spinner.getSelectedItem());
                Boolean f =true;
                if(!db.DataList_Course().isEmpty()){///course name already exist?
                    courseList=db.DataList_Course();
                    for(Course c : courseList){
                        if(c.getCourseName().equalsIgnoreCase(course_name))
                            f=false;
                    }
                }
                if(f==true)
                    db.Add_Course(teacher_name,course_name);
                else{
                    Toast toast = Toast.makeText(getApplicationContext(), "This course already exist!", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }
                Intent i =new Intent(getApplicationContext(),CoursesTeacher.class);
                startActivity(i);
            }
        });
    }

}

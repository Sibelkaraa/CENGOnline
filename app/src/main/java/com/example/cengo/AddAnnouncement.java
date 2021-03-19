package com.example.cengo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.List;

public class AddAnnouncement extends AppCompatActivity {

    String text,course_name,teacher_name;
    Spinner teacher,course;
    Button add;
    Database db=new Database(this);
    List<String> teachers_names= new ArrayList<String>();
    List<String> courses_names= new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_announcement);

        add=(Button)findViewById(R.id.button);
        teacher=(Spinner)findViewById(R.id.spinnerteacher);
        course=(Spinner)findViewById(R.id.spinnercourse);

        List<Teacher> teacherList=db.DataList_Teacher();
        for (Teacher t : teacherList){
            teachers_names.add(t.getName()+" "+t.getSurname());
        }
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,teachers_names);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        teacher.setAdapter(adapter);

        List<Course> courses_list=db.DataList_Course();
        for (Course c : courses_list){
            courses_names.add(c.getCourseName());
        }
        ArrayAdapter adapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,courses_names);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        course.setAdapter(adapter2);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                course_name=String.valueOf(teacher.getSelectedItem());
                teacher_name=String.valueOf(course.getSelectedItem());
                text=((EditText)findViewById(R.id.editText10)).getText().toString();
                db.Add_Announcements(course_name,teacher_name,text);
                Intent i =new Intent(getApplicationContext(),AnnouncementTeacher.class);
                startActivity(i);
            }
        });
    }
}

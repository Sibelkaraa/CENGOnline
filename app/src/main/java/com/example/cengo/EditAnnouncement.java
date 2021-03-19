package com.example.cengo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class EditAnnouncement extends AppCompatActivity {

    Spinner t, c;
    RecyclerView recycler_view;
    String course_name, teacher_name, oldcoursename;
    Button edit;
    EditText editText;
    Database db=new Database(this);
    List<Announcement> listAnnouncements = new ArrayList<Announcement>();
    List<Course> listCourses = new ArrayList<Course>();
    List<String> teacher_names= new ArrayList<String>();
    List<String> courses_names= new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_announcement);

        edit=(Button)findViewById(R.id.button2);
        c=(Spinner)findViewById(R.id.spinnercourse);
        t=(Spinner)findViewById(R.id.spinnerteacher);
        recycler_view = (RecyclerView) findViewById(R.id.view5);

        listAnnouncements=db.DataList_Announcements();
        List<String> al=new ArrayList<String>();

        List<Teacher> teacherList=db.DataList_Teacher();
        for (Teacher t : teacherList){
            teacher_names.add(t.getName()+" "+t.getSurname());
        }
        listCourses=db.DataList_Course();
        for (Course c : listCourses){
            courses_names.add(c.getCourseName());
        }

        ArrayAdapter adapterc = new ArrayAdapter(this,android.R.layout.simple_spinner_item,courses_names);
        adapterc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        c.setAdapter(adapterc);

        ArrayAdapter adaptert = new ArrayAdapter(this,android.R.layout.simple_spinner_item,teacher_names);
        adaptert.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        t.setAdapter(adaptert);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recycler_view.setLayoutManager(layoutManager);

        if (!db.DataList_Announcements().isEmpty()) {
            listAnnouncements = db.DataList_Announcements();
            SimpleRecyclerAdapterAnnouncements adapter_items = new SimpleRecyclerAdapterAnnouncements(listAnnouncements, new CustomItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    final Announcement a = listAnnouncements.get(position);
                    oldcoursename=a.getText();
                    edit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            course_name = String.valueOf(c.getSelectedItem());
                            teacher_name = String.valueOf(t.getSelectedItem());
                            editText=(EditText)findViewById(R.id.announcements4);
                            db.UpdateAnnouncements(oldcoursename, teacher_name, course_name,editText.getText().toString());
                            Intent i = new Intent(getApplicationContext(), AnnouncementTeacher.class);
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

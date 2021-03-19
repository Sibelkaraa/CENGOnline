package com.example.cengo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import com.santalu.maskedittext.MaskEditText;

import java.util.ArrayList;
import java.util.List;

public class AddHomework extends AppCompatActivity {

    Spinner spinner;
    EditText homework_name, explanation;
    MaskEditText due_date;
    Button add;
    List<String> listCourses=new ArrayList<String>();
    Database db = new Database(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_homework);

        spinner=(Spinner)findViewById(R.id.spinner);
        add=(Button)findViewById(R.id.addbutton);

        for(Course c: db.DataList_Course()){
            listCourses.add(c.CourseName.toString());
        }

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listCourses);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homework_name=(EditText)findViewById(R.id.homework_name_ans);
                explanation=(EditText)findViewById(R.id.exp_answer);
                due_date=(MaskEditText)findViewById(R.id.due_date_ans);
                db.Add_Homework(String.valueOf(spinner.getSelectedItem()),homework_name.getText().toString(),
                        explanation.getText().toString(),due_date.getText().toString(),R.drawable.homework);
                Intent i = new Intent(getApplicationContext(),AssignmentTeacher.class);
                startActivity(i);
            }
        });
    }
}

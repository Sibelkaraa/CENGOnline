package com.example.cengo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HomeworkUpload extends AppCompatActivity {

    TextView lesson_name,homework_name,explanation;
    EditText work;
    Button upload;
    Database db = new Database(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework_upload);

        upload=(Button)findViewById(R.id.upload);
        lesson_name=(TextView) findViewById(R.id.ust);
        homework_name=(TextView) findViewById(R.id.orta);
        explanation=(TextView) findViewById(R.id.alt);

        lesson_name.setText(Assignments_Student.lesson_name);
        homework_name.setText(Assignments_Student.homework_name);
        explanation.setText(Assignments_Student.explanation);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                work=(EditText)findViewById(R.id.work);
                String x = MainActivity.person_name+" "+MainActivity.person_surname;
                db.Add_Upload(Assignments_Student.lesson_name,Assignments_Student.homework_name,
                        x,MainActivity.person_email,work.getText().toString());
                Intent i = new Intent(getApplicationContext(),Assignments_Student.class);
                startActivity(i);
            }
        });
    }
}

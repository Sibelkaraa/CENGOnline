package com.example.cengo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class compose_email extends AppCompatActivity {

    Spinner spinner;
    EditText message;
    Button send;
    List<String> emails= new ArrayList<String>();
    Database db = new Database(this);
    String email;
    TextView from ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_email);

        spinner=(Spinner)findViewById(R.id.spinner2);
        message=(EditText)findViewById(R.id.message);
        send=(Button)findViewById(R.id.send);
        from=(TextView)findViewById(R.id.from);

        email=MainActivity.person_email;
        Create_Emails(email);
        from.setText(email);



        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,emails);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.Add_Message(email,String.valueOf(spinner.getSelectedItem()),message.getText().toString());
                Intent i = new Intent(getApplicationContext(), MessagesMainPage.class);
                startActivity(i);
            }
        });
    }
    private void Create_Emails(String person_mail){
        List<Teacher> teacherList=db.DataList_Teacher();
        for (Teacher t : teacherList){
            if(!t.getEmail().equals(person_mail))
                emails.add(t.getEmail());
        }
        List<Student> studentList=db.DataList_Student();
        for (Student t : studentList){
            if(!t.getEmail().equals(person_mail))
                emails.add(t.getEmail());
        }
    }


}

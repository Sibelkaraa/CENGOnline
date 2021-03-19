package com.example.cengo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static String person_name;
    public static String person_surname;
    public static String person_email;
    public static String person_type;
    private Button sign_up1, sign_in;
    private EditText email, password;
    private String emailstring, passwordstring;
    private boolean bool=false, control;
    private CheckBox checkBoxStudent, checkBoxTeacher;
    Database db = new Database(this);
    List<Student> dataStudent=new ArrayList<Student>();
    List<Teacher> dataTeacher=new ArrayList<Teacher>() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sign_up1=(Button)findViewById(R.id.signup);
        sign_in=(Button)findViewById(R.id.sign_in);

        if(!db.DataList_Student().isEmpty())
            dataStudent=db.DataList_Student();
        if(!db.DataList_Teacher().isEmpty())
            dataTeacher=db.DataList_Teacher();

        Login();


        sign_up1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),SignUp.class);
                startActivity(i);
            }
        });

    }
    public void Login(){

        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkBoxStudent=(CheckBox)findViewById(R.id.checkBox4) ;
                checkBoxTeacher=(CheckBox) findViewById(R.id.checkBox5);
                password=(EditText)findViewById(R.id.editText);
                email=(EditText)findViewById(R.id.editText5);
                passwordstring=password.getText().toString();
                emailstring=email.getText().toString();

                control=true;
                if(email.getText().toString().trim().length() == 0){//name text is blank?
                    email.setError("Can not be blank");
                    control=false;
                }
                if (password.getText().toString().trim().length() == 0){//surname text is blank?
                    password.setError("Can not be blank");
                    control=false;
                }
                if((checkBoxStudent.isChecked())&&(checkBoxTeacher.isChecked())){
                    Toast toast = Toast.makeText(getApplicationContext(), "You can only select 1 checkbox!", Toast.LENGTH_LONG);
                    toast.show();
                }
                else if((!checkBoxStudent.isChecked())&&(!checkBoxTeacher.isChecked())){
                    Toast toast = Toast.makeText(getApplicationContext(), "Please select student or teacher!", Toast.LENGTH_LONG);
                    toast.show();
                }
                else{
                    if((checkBoxStudent.isChecked())&&(control==true)) {////student login
                        for (Student s : dataStudent) {
                            if ((s.getEmail().equals(emailstring)) && (s.getPassword().equals(passwordstring))) {
                                Intent i = new Intent(getApplicationContext(),MainPageStudent.class);
                                person_name=s.getName();
                                person_surname=s.getSurname();
                                person_email=s.getEmail();
                                person_type="student";
                                startActivity(i);
                                bool = true;
                            }
                        }
                        if (bool == false) {
                            Toast x = Toast.makeText(getApplicationContext(), "Authentication involved!", Toast.LENGTH_LONG);
                            x.show();
                        }
                    }
                    if((checkBoxTeacher.isChecked())&&(control==true)){///teacher login
                        for (Teacher t : dataTeacher){
                            if ((t.getEmail().equals(emailstring))&&(t.getPassword().equals(passwordstring))){
                                Intent i = new Intent(getApplicationContext(),MainPageTeacher.class);
                                person_name=t.getName();
                                person_surname=t.getSurname();
                                person_email=t.getEmail();
                                person_type="teacher";
                                startActivity(i);
                                bool=true;
                            }
                        }
                        if(bool==false){
                            Toast x = Toast.makeText(getApplicationContext(), "Authentication involved!", Toast.LENGTH_LONG);
                            x.show();
                        }
                    }
                }

            }
        });
    }

}

package com.example.cengo;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;


public class SignUp extends AppCompatActivity {
     private Button sign_up2;
     private ListView listV;
     private String name, surname,email, password;
     private CheckBox checkBoxStudent, checkBoxTeacher;
     private boolean control=false,control2=true;
     private EditText inputName, inputSurname, inputEmail, inputPassword;

     Database db=new Database(this);
     List<Student> listStudent = new ArrayList<Student>();
     List<Teacher> listTeacher = new ArrayList<Teacher>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        sign_up2=(Button)findViewById(R.id.signup2);
        if(!db.DataList_Student().isEmpty()){
            listStudent=db.DataList_Student();
        }
        if(!db.DataList_Teacher().isEmpty()){
            listTeacher=db.DataList_Teacher();
        }
        Registration();

    }

    private void Registration(){
        sign_up2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputName=(EditText)findViewById(R.id.editText2) ;
                inputSurname=(EditText)findViewById(R.id.editText3) ;
                inputEmail=(EditText)findViewById(R.id.editText5);
                inputPassword = (EditText) findViewById(R.id.editText8);
                name=inputName.getText().toString();
                surname=inputSurname.getText().toString();
                email=inputEmail.getText().toString();
                password=inputPassword.getText().toString();
                checkBoxStudent=(CheckBox)findViewById(R.id.student);
                checkBoxTeacher=(CheckBox)findViewById(R.id.teacher) ;

                control=true;
                if(inputName.getText().toString().trim().length() == 0){//name text is blank?
                    inputName.setError("Can not be blank");
                    control=false;
                }
                if (inputSurname.getText().toString().trim().length() == 0){//surname text is blank?
                    inputSurname.setError("Can not be blank");
                    control=false;
                }
                if (inputEmail.getText().toString().trim().length() == 0){//email text is blank?
                    inputEmail.setError("Can not be blank");
                    control=false;
                }
                if (inputPassword.getText().toString().trim().length() == 0){//password text is blank?
                    inputPassword.setError("Can not be blank");
                    control=false;
                }
                if((checkBoxStudent.isChecked())&&(control==true)){
                    if(!listStudent.isEmpty()){
                        for(Student stu: listStudent){//Have you already registered with this email address?
                            if(stu.getEmail().equals(email)){
                                control2=false;
                                Toast toast = Toast.makeText(getApplicationContext(), "This email is already registered", Toast.LENGTH_LONG);
                                toast.show();
                            }
                        }
                    }
                }
                if((checkBoxTeacher.isChecked())&&(control==true)){
                    if(!listTeacher.isEmpty()){
                        for(Teacher teacher: listTeacher){//Have you already registered with this email address?
                            if(teacher.getEmail().equals(email)){
                                control2=false;
                                Toast toast = Toast.makeText(getApplicationContext(), "This email is already registered", Toast.LENGTH_LONG);
                                toast.show();
                            }
                        }
                    }
                }
                if ((control==true)&&(control2==true)){
                    if((checkBoxStudent.isChecked())&&(checkBoxTeacher.isChecked())){
                        Toast toast = Toast.makeText(getApplicationContext(), "You can only select 1 checkbox!", Toast.LENGTH_LONG);
                        toast.show();
                    }
                    else if((!checkBoxStudent.isChecked())&&(!checkBoxTeacher.isChecked())){
                        Toast toast = Toast.makeText(getApplicationContext(), "Please select student or teacher!", Toast.LENGTH_LONG);
                        toast.show();
                    }
                    else{
                        if((checkBoxStudent.isChecked())&&(!checkBoxTeacher.isChecked())){///add student
                            db.Add_Student(name,surname,email,password);
                            listStudent = db.DataList_Student();
                            Toast toast = Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_LONG);
                            toast.show();
                            Intent i = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(i);
                        }
                        else if((!checkBoxStudent.isChecked())&&(checkBoxTeacher.isChecked())){////add teacher
                            db.Add_Teacher(name,surname,email,password);
                            listTeacher = db.DataList_Teacher();
                            Toast toast = Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_LONG);
                            toast.show();
                            Intent i = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(i);
                        }
                    }


                }

            }
        });
    }

}

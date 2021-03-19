package com.example.cengo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainPageStudent extends AppCompatActivity {

    private ActionBar actionBar;
    private Menu optionsMenu;
    Button courses, announcements, messaging, assignments, sign_out;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page_student);


        getSupportActionBar().setSubtitle(MainActivity.person_name+" "+MainActivity.person_surname+" ("+MainActivity.person_type+")");

        courses=(Button)findViewById(R.id.button5);
        announcements=(Button)findViewById(R.id.button6);
        messaging=(Button)findViewById(R.id.button7);
        assignments=(Button)findViewById(R.id.button8);

        courses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),CoursesStudent.class);
                startActivity(i);
            }
        });

        announcements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),AnnouncementStudent.class);
                startActivity(i);
            }
        });
        messaging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MessagesMainPage.class);
                startActivity(i);

            }
        });
        assignments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Assignments_Student.class);
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//Menüyü oluşturuyoruz
        this.optionsMenu = menu;

        MenuInflater inflater = getMenuInflater();// inflater herhangi bir view ın java objesine dönüştürülüp düzenlenmesinde yardımcı olur.Burda menü düzenlenmesi için kullanacağız
        inflater.inflate(R.menu.sign_out, menu);//Xml olarak oluşturduğumuz menü yü alıyoruz

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {// Action Bar itemden herhangi biri tıklandığında

        switch (item.getItemId()) {

            case R.id.sign_out: //add iconu tıklandığında

                Intent i =new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);

                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

}

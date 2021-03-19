package com.example.cengo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainPageTeacher extends AppCompatActivity {

    Button courses, announcements, messaging, assignments, sign_out, uploads;
    private ActionBar actionBar;
    private Menu optionsMenu;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page_teacher);

        getSupportActionBar().setSubtitle(MainActivity.person_name+" "+MainActivity.person_surname+" ("+MainActivity.person_type+")");

        courses=(Button)findViewById(R.id.courses);
        announcements=(Button)findViewById(R.id.annoucement);
        messaging=(Button)findViewById(R.id.messaging);
        assignments=(Button)findViewById(R.id.assignment);
        uploads=(Button)findViewById(R.id.upload) ;


        courses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),CoursesTeacher.class);
                startActivity(i);
            }
        });

        announcements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),AnnouncementTeacher.class);
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
                Intent i = new Intent(getApplicationContext(),AssignmentTeacher.class);
                startActivity(i);
            }
        });
        uploads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),SubmissionsMain.class);
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

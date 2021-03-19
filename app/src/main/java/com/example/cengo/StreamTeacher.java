package com.example.cengo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class StreamTeacher extends AppCompatActivity {

    Database db = new Database(this);
    RecyclerView recycler_view;
    ImageButton add;
    List<Post> listPost = new ArrayList<Post>();

    private ActionBar actionBar;
    private Menu optionsMenu;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stream_teacher);
        actionBar = getActionBar();


        recycler_view=(RecyclerView)findViewById(R.id.recyclerView15);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recycler_view.setLayoutManager(layoutManager);


        if(!db.DataList_Post().isEmpty()){
            for(Post p: db.DataList_Post()){
                if(p.getCourse_name().equals(CoursesTeacher.course_name)){
                    listPost.add(p);
                }
            }
            SimpleRecyclerAdapterStream adapter_items = new SimpleRecyclerAdapterStream(listPost, new CustomItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {

                }
            });

            recycler_view.setHasFixedSize(true);

            recycler_view.setAdapter(adapter_items);

            recycler_view.setItemAnimator(new DefaultItemAnimator());

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//Menüyü oluşturuyoruz
        this.optionsMenu = menu;

        MenuInflater inflater = getMenuInflater();// inflater herhangi bir view ın java objesine dönüştürülüp düzenlenmesinde yardımcı olur.Burda menü düzenlenmesi için kullanacağız
        inflater.inflate(R.menu.add_menu, menu);//Xml olarak oluşturduğumuz menü yü alıyoruz

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {// Action Bar itemden herhangi biri tıklandığında

        switch (item.getItemId()) {

            case R.id.add17: //add iconu tıklandığında
                showMyCustomAlertDialog();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    public void showMyCustomAlertDialog() {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.custom_alert_diaolg);

        final Button cancel = (Button) dialog.findViewById(R.id.cancel);
        Button addPost = (Button) dialog.findViewById(R.id.add_post);
        TextView teacherName = (TextView) dialog.findViewById(R.id.Teacher_name);
        final EditText newPost = (EditText) dialog.findViewById(R.id.new_post);

        teacherName.setText(MainActivity.person_name+" "+MainActivity.person_surname);

        addPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!newPost.getText().toString().trim().equals("")){
                    db.Add_Post(CoursesTeacher.course_name,MainActivity.person_name+" "+MainActivity.person_surname,newPost.getText().toString());
                    dialog.dismiss();
                    finish();
                    startActivity(getIntent());
                }
              }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

}


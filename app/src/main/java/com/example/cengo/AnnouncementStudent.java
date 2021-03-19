package com.example.cengo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class AnnouncementStudent extends AppCompatActivity {

    private ImageButton back;
    private RecyclerView recycler_view;
    Database db= new Database(this);
    List<Announcement> listAnnouncements = new ArrayList<Announcement>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement_student);


        recycler_view = (RecyclerView)findViewById(R.id.view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recycler_view.setLayoutManager(layoutManager);

        if(!db.DataList_Announcements().isEmpty()){
            listAnnouncements = db.DataList_Announcements();
            SimpleRecyclerAdapterAnnouncements adapter_items = new SimpleRecyclerAdapterAnnouncements(listAnnouncements, new CustomItemClickListener() {
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
    public void onBackPressed() {
        super.onBackPressed();
        if(MainActivity.person_type.equals("teacher")){
            Intent i = new Intent(getApplicationContext(),MainPageTeacher.class);
            startActivity(i);
        }
        else{
            Intent i = new Intent(getApplicationContext(),MainPageStudent.class);
            startActivity(i);
        }
    }
}

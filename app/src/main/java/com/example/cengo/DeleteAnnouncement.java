package com.example.cengo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class DeleteAnnouncement extends AppCompatActivity {

    Database db=new Database(this);
    List<Announcement> listAnnouncement = new ArrayList<Announcement>();
    RecyclerView recycler_view;
    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_announcement);

        delete=(Button)findViewById(R.id.deletebutton5);
        listAnnouncement=db.DataList_Announcements();
        recycler_view=(RecyclerView)findViewById(R.id.viev14);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recycler_view.setLayoutManager(layoutManager);

        if(!db.DataList_Announcements().isEmpty()){
            listAnnouncement = db.DataList_Announcements();
            SimpleRecyclerAdapterAnnouncements adapter_items = new SimpleRecyclerAdapterAnnouncements(listAnnouncement, new CustomItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    final Announcement a= listAnnouncement.get(position);
                    delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            db.DeleteAnnouncements(a.getText());
                            Intent i = new Intent(getApplicationContext(),AnnouncementTeacher.class);
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

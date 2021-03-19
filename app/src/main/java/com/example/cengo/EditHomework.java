package com.example.cengo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.santalu.maskedittext.MaskEditText;

import java.util.List;

public class EditHomework extends AppCompatActivity {

    private Button edit;
    MaskEditText date;
    EditText homework_name, explanation;
    private RecyclerView recycler_view;
    private List<Homework> homeworkList;
    Database db = new Database(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_homework);
        edit=(Button)findViewById(R.id.edit);
        recycler_view=(RecyclerView)findViewById(R.id.recycler_view1);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recycler_view.setLayoutManager(layoutManager);

        if(!db.DataList_Homework().isEmpty()){
            homeworkList = db.DataList_Homework();
            SimpleRecyclerAdapter adapter_items = new SimpleRecyclerAdapter(homeworkList, new CustomItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    final Homework homework = homeworkList.get(position);
                    edit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            homework_name=(EditText)findViewById(R.id.homeworkname) ;
                            explanation=(EditText)findViewById(R.id.explanation);
                            date=(MaskEditText)findViewById(R.id.duedate) ;
                            db.UpdateHomework(homework.getHw_name(),homework_name.getText().toString(),
                                    explanation.getText().toString(), date.getText().toString());
                            Intent i = new Intent(getApplicationContext(),AssignmentTeacher.class);
                            startActivity(i);
                        }
                    });
                    Toast.makeText(getApplicationContext(),"pozisyon:"+" "+position+" "+"Ad:"+homework.getHw_name(), Toast.LENGTH_SHORT).show();

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
            Intent i = new Intent(getApplicationContext(),MainPageTeacher.class);
            startActivity(i);
        }
    }
}

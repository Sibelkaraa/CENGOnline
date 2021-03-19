package com.example.cengo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SubmissionsList extends AppCompatActivity {

    ListAdapter lAdapter;
    ListView list;
    TextView title;
    Database db= new Database(this);
    List<Upload> listUpload = new ArrayList<Upload>();
    List<Upload> myUpload = new ArrayList<Upload>();

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submissions_list);
        list=(ListView)findViewById(R.id.list);
        title=(TextView)findViewById(R.id.baslik);


        title.setText("Submissions\n"+"(" + SubmissionsMain.hw.getLesson_name().toUpperCase().toString()
                +" - "+ SubmissionsMain.hw.getHw_name().toString()+")");
        int x=0;
        for(Upload u : db.DataList_Upload()) {
            if ((u.getLesson_name().equals(SubmissionsMain.hw.getLesson_name()))&&(u.getHomework_name().equals(SubmissionsMain.hw.getHw_name()))){
                x++;
                myUpload.add(u);
            }
        }
        int[] images = new int[x] ;
        String[] name_surname= new String[x];
        String[] work=new String[x];
        if(x==0){
            Toast toast = Toast.makeText(getApplicationContext(), "There is no submissions yet for this assignment", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            list.setVisibility(View.INVISIBLE);
        }
        else{
            list.setVisibility(View.VISIBLE);
            name_surname=CreateNameSurname();
            work=CreateWork();
            images=CreateImages();
            lAdapter = new ListAdapter(SubmissionsList.this, name_surname, work, images);
            list.setAdapter(lAdapter);
            Windows(name_surname,work,images);


        }
    }

    private void Windows(final String[] name_surname, final String[] work, final int[] images){
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                AlertDialog.Builder dialog =
                        new AlertDialog.Builder(SubmissionsList.this,R.style.MyDialogTheme);

                dialog.setIcon(images[position]) ;
                dialog.setMessage(work[position])
                        .setTitle(name_surname[position])
                        .setCancelable(false)
                        .setPositiveButton("Okay✓", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                dialog.show();
            }
        });
    }


    private String[] CreateNameSurname(){
        String[] temp=new String[myUpload.size()];
        int count=0;
        for(Upload u: myUpload){
            temp[count]=u.getStudent_name();
            count++;
        }
        return temp;
    }

    private String[] CreateWork(){
        String[] temp=new String[myUpload.size()];
        int count=0;
        for(Upload u: myUpload){
            temp[count]=u.getWork();
            count++;
        }
        return temp;
    }

    private int[] CreateImages(){
        int[] temp=new int[myUpload.size()];
        int count=0;
        for(Upload u: myUpload){
            Random rndm= new Random();
            int selection =rndm.nextInt(6)+1;
            switch (selection){
                case 1 :
                    temp[count]=R.drawable.kirmizi;
                    count++;
                    break;
                case 2 :
                    temp[count]=R.drawable.mavi;
                    count++;
                    break;
                case 3:
                    temp[count]=R.drawable.pembe;
                    count++;
                    break;
                case 4 :
                    temp[count]=R.drawable.sari;
                    count++;
                    break;
                case 5 :
                    temp[count]=R.drawable.turuncu;
                    count++;
                    break;
                case 6 :
                    temp[count]=R.drawable.yesil;
                    count++;
                    break;
            }
        }
        return temp;
    }
}

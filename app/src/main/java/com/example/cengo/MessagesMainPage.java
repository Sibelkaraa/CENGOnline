package com.example.cengo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MessagesMainPage extends AppCompatActivity {

    Button compose, inbox,sent;
    ListView list;
    ListAdapter lAdapter;

    Database db= new Database(this);
    List<Message> listMessage = new ArrayList<Message>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages_main_page);

        compose=(Button)findViewById(R.id.compose);
        inbox=(Button)findViewById(R.id.inbox);
        sent=(Button)findViewById(R.id.sent);
        list=(ListView)findViewById(R.id.list7);

        compose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),compose_email.class);
                startActivity(i);
            }
        });
        inbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int x=0;
                for(Message m : db.DataList_Message()) {
                    if (m.getTo_who().equals(MainActivity.person_email))
                        x++;
                }
                int[] images = new int[x] ;
                String[] emails= new String[x];
                String[] messages=new String[x];
                if(x==0){
                    Toast toast = Toast.makeText(getApplicationContext(), "boş", Toast.LENGTH_LONG);
                    toast.show();
                    list.setVisibility(View.INVISIBLE);
                }
                else{
                    list.setVisibility(View.VISIBLE);
                    List_Email_message(emails,messages,MainActivity.person_email,"inbox");
                    Inbox_Create_Images(images);
                    lAdapter = new ListAdapter(MessagesMainPage.this, emails, messages, images);
                    list.setAdapter(lAdapter);
                }
                MessageBox(list,emails,messages,"inbox");
            }
        });

        sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x=0;
                for(Message m : db.DataList_Message()) {
                    if (m.getFrom_who().equals(MainActivity.person_email))
                        x++;
                }
                int[] images = new int[x] ;
                String[] emails= new String[x];
                String[] messages=new String[x];
                if(x==0){
                    list.setVisibility(View.INVISIBLE);
                }
                else{
                    list.setVisibility(View.VISIBLE);
                    List_Email_message(emails,messages,MainActivity.person_email);
                    Sent_Create_Images(images);
                    lAdapter = new ListAdapter(MessagesMainPage.this, emails, messages, images);
                    list.setAdapter(lAdapter);
                }
                MessageBox(list,emails,messages,"sent");
            }
        });
    }


    public void List_Email_message(String []emails2,String[] messages2,String email,String type){//inbox
        int count=0;
        if(!db.DataList_Message().isEmpty()){
            listMessage=db.DataList_Message();
            for (int i = 0; i<listMessage.size(); i++){
                if(listMessage.get(i).getTo_who().equals(email)){
                    emails2[count]=listMessage.get(i).getFrom_who();
                    messages2[count]=listMessage.get(i).getMessage_text();
                    count++;
                }
            }
        }
    }
    public void List_Email_message(String []emails2,String[] messages2,String email){
        int count=0;
        if(!db.DataList_Message().isEmpty()){
            listMessage=db.DataList_Message();
            for (int i = 0; i<listMessage.size(); i++){
                if(listMessage.get(i).getFrom_who().equals(email)){
                    emails2[count]=listMessage.get(i).getTo_who();
                    messages2[count]=listMessage.get(i).getMessage_text();
                    count++;
                }
            }
        }
    }


    private void Inbox_Create_Images(int[]images2){
        int count=0;
        if(!db.DataList_Message().isEmpty()){
            listMessage=db.DataList_Message();
            for (int i=0; i<listMessage.size();i++){
                if(listMessage.get(i).getTo_who().equals(MainActivity.person_email)){
                    Random rndm= new Random();
                    int selection =rndm.nextInt(6)+1;
                    switch (selection){
                        case 1 :
                            images2[count]=R.drawable.kirmizi;
                            count++;
                            break;
                        case 2 :
                            images2[count]=R.drawable.mavi;
                            count++;
                            break;
                        case 3:
                            images2[count]=R.drawable.pembe;
                            count++;
                            break;
                        case 4 :
                            images2[count]=R.drawable.sari;
                            count++;
                            break;
                        case 5 :
                            images2[count]=R.drawable.turuncu;
                            count++;
                            break;
                        case 6 :
                            images2[count]=R.drawable.yesil;
                            count++;
                            break;
                    }
                }
            }
        }
    }
    private void Sent_Create_Images(int[]images2){
        int count=0;
        if(!db.DataList_Message().isEmpty()){
            listMessage=db.DataList_Message();
            for (int i=0; i<listMessage.size();i++){
                if(listMessage.get(i).getFrom_who().equals(MainActivity.person_email)){
                    Random rndm= new Random();
                    int selection =rndm.nextInt(6)+1;
                    switch (selection){
                        case 1 :
                            images2[count]=R.drawable.kirmizi;
                            count++;
                            break;
                        case 2 :
                            images2[count]=R.drawable.mavi;
                            count++;
                            break;
                        case 3:
                            images2[count]=R.drawable.pembe;
                            count++;
                            break;
                        case 4 :
                            images2[count]=R.drawable.sari;
                            count++;
                            break;
                        case 5 :
                            images2[count]=R.drawable.turuncu;
                            count++;
                            break;
                        case 6 :
                            images2[count]=R.drawable.yesil;
                            count++;
                            break;
                    }
                }
            }
        }
    }

    private void MessageBox(ListView l, final String[] emails, final String[] messages, final String type){
        listMessage=db.DataList_Message();

        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView <?> parent, View view, int position, long id) {

                if(type.equals("inbox")){
                    for(Message m : listMessage){
                        if((m.getFrom_who().equals(emails[position]))&&(m.getMessage_text().equals(messages[position]))){
                            AlertDialog.Builder builder = new AlertDialog.Builder(MessagesMainPage.this,R.style.MyDialogTheme);
                            builder.setTitle("FROM: "+m.from_who);
                            builder.setMessage(m.getMessage_text());
                            builder.setPositiveButton("CANCEL", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                }
                            });
                            builder.show();
                        }
                    }
                }
                else{
                    for(Message m : listMessage){
                        if((m.getTo_who().equals(emails[position]))&&(m.getMessage_text().equals(messages[position]))){
                            AlertDialog.Builder builder = new AlertDialog.Builder(MessagesMainPage.this,R.style.MyDialogTheme);
                            builder.setTitle("TO: "+m.getTo_who());
                            builder.setMessage(m.getMessage_text());
                            builder.setPositiveButton("CANCEL", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                }
                            });
                            builder.show();
                        }
                    }
                }

            }
        });
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

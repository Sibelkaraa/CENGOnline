package com.example.cengo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Database extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "sqlite_database";

    private static final String ROW_ID_STUDENT="id";
    private static final String STUDENT_TABLE="Student";
    private static final String NAME_STUDENT="name";
    private static final String SURNAME_STUDENT="surname";
    private static final String USERNAME_STUDENT="username";
    private static final String PASSWORD_STUDENT="password";

    private static final String ROW_ID_TEACHER="id";
    private static final String TEACHER_TABLE="Teacher";
    private static final String NAME_TEACHER="Teachername";
    private static final String SURNAME_TEACHER="Teachersurname";
    private static final String USERNAME_TEACHER="Teacherusername";
    private static final String PASSWORD_TEACHER="Teacherpassword";

    private static final String ID_COURSE="id";
    private static final String COURSE_TABLE="Courses";
    private static final String COURSE_TEACHER="Teachername";
    private static final String COURSES_NAME="Coursename";

    private static final String ID_ANNOUNCEMENTS="id";
    private static final String ANNOUNCEMENTS_TABLE="Announcements";
    private static final String ANNOUNCEMENTS_COURSE="AnnouncementCourse";
    private static final String ANNOUNCEMENTS_TEACHER="Teachername";
    private static final String ANNOUNCEMENTS_TEXT="AnnouncementText";

    private static final String MESSAGES_TABLE="messsage";
    private static final String MESSAGES_FROM="fromwho";
    private static final String MESSAGES_TO="towho";
    private static final String MESSAGES_TEXT="text";

    private static final String HOMEWORK_TABLE="homework";
    private static final String LESSON_NAME="lesson_name";
    private static final String HOMEWORK_NAME="homework_name";
    private static final String EXPLANATION="explanation";
    private static final String DUE_DATE="due_date";
    private static final String PHOTO_ID="photo_id";

    private static final String UPLOAD_TABLE="upload";
    private static final String UPLOAD_LESSON_NAME="lesson_name";
    private static final String UPLOAD_HOMEWORK_NAME="homework_name";
    private static final String UPLOAD_STUDENT_NAME="student_name";
    private static final String UPLOAD_STUDENT_EMAIL="student_email";
    private static final String WORK="work";

    private static final String POST_TABLE="post_table";
    private static final String COURSE_NAME="coursename";
    private static final String POST_ID="id";
    private static final String TEACHER_NAME="teachername";
    private static final String POST="post";

    private static final String COMMENT_TABLE="comment_table";
    private static final String POSTT="post";
    private static final String PERSON_NAME="personname";
    private static final String COMMENT="comment";


    private static final String Create_Student_table = "CREATE TABLE " + STUDENT_TABLE + "("
            + ROW_ID_STUDENT + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NAME_STUDENT + " TEXT NOT NULL, "
            + SURNAME_STUDENT + " TEXT NOT NULL, "
            + USERNAME_STUDENT + " TEXT NOT NULL, "
            + PASSWORD_STUDENT + " TEXT NOT NULL " + ")";

    private static final String Create_Teacher_table = "CREATE TABLE "+ TEACHER_TABLE + "("
            + ROW_ID_TEACHER + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NAME_TEACHER + " TEXT NOT NULL, "
            + SURNAME_TEACHER + " TEXT NOT NULL, "
            + USERNAME_TEACHER + " TEXT NOT NULL, "
            + PASSWORD_TEACHER + " TEXT NOT NULL " + ")";

    private static final String Create_Courses_table = "CREATE TABLE "+ COURSE_TABLE + "("
            + ID_COURSE + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COURSE_TEACHER + " TEXT NOT NULL, "
            + COURSES_NAME + " TEXT NOT NULL " + ")";

    private static final String Create_Announcements_table = "CREATE TABLE "+ ANNOUNCEMENTS_TABLE + "("
            + ID_ANNOUNCEMENTS + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ANNOUNCEMENTS_COURSE + " TEXT NOT NULL, "
            + ANNOUNCEMENTS_TEACHER + " TEXT NOT NULL, "
            + ANNOUNCEMENTS_TEXT + " TEXT NOT NULL " + ")";

    private static final String Create_Messages_table = "CREATE TABLE "+ MESSAGES_TABLE + "("
            + MESSAGES_FROM + " TEXT NOT NULL, "
            + MESSAGES_TO + " TEXT NOT NULL, "
            + MESSAGES_TEXT + " TEXT NOT NULL "+ ")";

    private static final String Create_Homework_table = "CREATE TABLE "+ HOMEWORK_TABLE + "("
            + LESSON_NAME + " TEXT NOT NULL, "
            + HOMEWORK_NAME + " TEXT NOT NULL, "
            + EXPLANATION + " TEXT NOT NULL, "
            + DUE_DATE + " TEXT NOT NULL, "
            + PHOTO_ID + " INTEGER NOT NULL " + ")";

    private static final String Create_Upload_table = "CREATE TABLE "+ UPLOAD_TABLE + "("
            + UPLOAD_LESSON_NAME + " TEXT NOT NULL, "
            + UPLOAD_HOMEWORK_NAME + " TEXT NOT NULL, "
            + UPLOAD_STUDENT_NAME + " TEXT NOT NULL, "
            + UPLOAD_STUDENT_EMAIL + " TEXT NOT NULL, "
            + WORK + " TEXT NOT NULL " + ")";

    private static final String Create_post_table = "CREATE TABLE "+ POST_TABLE + "("
            + POST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COURSE_NAME + " TEXT NOT NULL, "
            + TEACHER_NAME + " TEXT NOT NULL, "
            + POST + " TEXT NOT NULL " + ")";

    private static final String Create_comment_table = "CREATE TABLE "+ COMMENT_TABLE + "("
            + POSTT + " TEXT NOT NULL, "
            + PERSON_NAME + " TEXT NOT NULL, "
            + COMMENT + " TEXT NOT NULL " + ")";

    public Database(Context c) {
        super(c, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Create_Teacher_table);
        db.execSQL(Create_Student_table);
        db.execSQL(Create_Courses_table);
        db.execSQL(Create_Announcements_table);
        db.execSQL(Create_Messages_table);
        db.execSQL(Create_Homework_table);
        db.execSQL(Create_Upload_table);
        db.execSQL(Create_post_table);
        db.execSQL(Create_comment_table);
    }

    public void Add_comment(String postt,String person_name,String comment){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(POSTT, postt);
        values.put(PERSON_NAME,person_name);
        values.put(COMMENT,comment);
        db.insert(COMMENT_TABLE,null,values);
        db.close();
    }
    public void Add_Post(String course_name,String teacher_name,String post){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(COURSE_NAME, course_name);
        values.put(TEACHER_NAME,teacher_name);
        values.put(POST,post);
        db.insert(POST_TABLE,null,values);
        db.close();
    }
    public void Add_Student(String name,String surname,String username, String password){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(NAME_STUDENT,name);
        values.put(SURNAME_STUDENT,surname);
        values.put(USERNAME_STUDENT,username);
        values.put(PASSWORD_STUDENT,password);
        db.insert(STUDENT_TABLE,null,values);
        db.close();
    }
    public void Add_Teacher(String name,String surname,String username, String password){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values2= new ContentValues();
        values2.put(NAME_TEACHER,name);
        values2.put(SURNAME_TEACHER,surname);
        values2.put(USERNAME_TEACHER,username);
        values2.put(PASSWORD_TEACHER,password);
        db.insert(TEACHER_TABLE,null,values2);
        db.close();
    }
    public void Add_Course(String teacher_name,String course_name){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values2= new ContentValues();
        values2.put(COURSE_TEACHER,teacher_name);
        values2.put(COURSES_NAME,course_name);
        db.insert(COURSE_TABLE,null,values2);
        db.close();
    }
    public void Add_Announcements(String course_name,String teacher_name,String text){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(ANNOUNCEMENTS_COURSE,course_name);
        values.put(ANNOUNCEMENTS_TEACHER,teacher_name);
        values.put(ANNOUNCEMENTS_TEXT,text);
        db.insert(ANNOUNCEMENTS_TABLE,null,values);
        db.close();
    }
    public void Add_Message(String from,String to,String text){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(MESSAGES_FROM,from);
        values.put(MESSAGES_TO,to);
        values.put(MESSAGES_TEXT,text);
        db.insert(MESSAGES_TABLE,null,values);
        db.close();
    }
    public void Add_Upload(String lesson_name, String homework_name, String student_name, String student_email, String work){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(UPLOAD_LESSON_NAME,lesson_name);
        values.put(UPLOAD_HOMEWORK_NAME,homework_name);
        values.put(UPLOAD_STUDENT_NAME,student_name);
        values.put(UPLOAD_STUDENT_EMAIL,student_email);
        values.put(WORK,work);
        db.insert(UPLOAD_TABLE,null,values);
        db.close();
    }
    public void Add_Homework(String lesson_name, String homework_name, String explanation, String due_date, int photo_id){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(LESSON_NAME,lesson_name);
        values.put(HOMEWORK_NAME,homework_name);
        values.put(EXPLANATION,explanation);
        values.put(DUE_DATE,due_date);
        values.put(PHOTO_ID,photo_id);
        db.insert(HOMEWORK_TABLE,null,values);
        db.close();
    }

   public List<Post> DataList_Post(){
        List<Post> data = new ArrayList<Post>();
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            String[] rows = {COURSE_NAME,TEACHER_NAME,POST};
            Cursor cursor = db.query(POST_TABLE, rows,null,null,null,null,null);
            while (cursor.moveToNext()){
                Post p = new Post(cursor.getString(0),cursor.getString(1),cursor.getString(2));
                data.add(p);
            }
        }catch (Exception e){
        }
        db.close();
        return data;
    }

    public List<Comment> DataList_Comment(String post){
        List<Comment> data = new ArrayList<Comment>();
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            String[] rows = {POSTT,PERSON_NAME,COMMENT};
            Cursor cursor = db.query(COMMENT_TABLE, rows,null,null,null,null,null);
            while (cursor.moveToNext()){
                if(cursor.getString(0).equals(post)){
                    Comment p = new Comment(cursor.getString(0),cursor.getString(1),cursor.getString(2));
                    data.add(p);
                }
            }
        }catch (Exception e){
        }
        db.close();
        return data;
    }

    public List<Student> DataList_Student(){
        List<Student> data = new ArrayList<Student>();
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            String[] rows = {NAME_STUDENT,SURNAME_STUDENT,USERNAME_STUDENT,PASSWORD_STUDENT};
            Cursor cursor = db.query(STUDENT_TABLE, rows,null,null,null,null,null);
            while (cursor.moveToNext()){
                Student s = new Student(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));
                data.add(s);
            }
        }catch (Exception e){
        }
        db.close();
        return data;
    }
    public List<Teacher> DataList_Teacher(){
        List<Teacher> dataTeacher = new ArrayList<Teacher>();
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            String[] rows = {NAME_TEACHER,SURNAME_TEACHER,USERNAME_TEACHER,PASSWORD_TEACHER};
            Cursor cursor = db.query(TEACHER_TABLE, rows,null,null,null,null,null);
            while (cursor.moveToNext()){
                Teacher s = new Teacher(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));
                dataTeacher.add(s);
            }
        }catch (Exception e){
        }
        db.close();
        return dataTeacher;
    }

    public List<Course> DataList_Course(){
        List<Course> dataCourse = new ArrayList<Course>();
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            String[] rows = {COURSE_TEACHER,COURSES_NAME};
            Cursor cursorr = db.query(COURSE_TABLE, rows,null,null,null,null,null);
            while (cursorr.moveToNext()){
                Course s = new Course(cursorr.getString(0),cursorr.getString(1));
                dataCourse.add(s);
            }
        }catch (Exception e){
        }
        db.close();
        return dataCourse;
    }
    public List<Announcement> DataList_Announcements(){
        List<Announcement> dataAnnouncements = new ArrayList<Announcement>();
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            String[] rows = {ANNOUNCEMENTS_COURSE,ANNOUNCEMENTS_TEACHER,ANNOUNCEMENTS_TEXT};
            Cursor cursor = db.query(ANNOUNCEMENTS_TABLE, rows,null,null,null,null,null);
            while (cursor.moveToNext()){
                Announcement s = new Announcement(cursor.getString(0),cursor.getString(1),cursor.getString(2));
                dataAnnouncements.add(s);
            }
        }catch (Exception e){
        }
        db.close();
        return dataAnnouncements;
    }
    public List<Message> DataList_Message(){
        List<Message> dataMessage = new ArrayList<Message>();
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            String[] rows = {MESSAGES_FROM,MESSAGES_TO,MESSAGES_TEXT};
            Cursor cursor = db.query(MESSAGES_TABLE, rows,null,null,null,null,null);
            while (cursor.moveToNext()){
                Message s = new Message(cursor.getString(0),cursor.getString(1),cursor.getString(2));
                dataMessage.add(s);
            }
        }catch (Exception e){
        }
        db.close();
        return dataMessage;
    }

    public List<Homework> DataList_Homework(){
        List<Homework> dataHomework = new ArrayList<Homework>();
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            String[] rows = {LESSON_NAME,HOMEWORK_NAME,EXPLANATION,DUE_DATE,PHOTO_ID};
            Cursor cursor = db.query(HOMEWORK_TABLE, rows,null,null,null,null,null);
            while (cursor.moveToNext()){
                Homework s = new Homework(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getInt(4));
                dataHomework.add(s);
            }
        }catch (Exception e){
        }
        db.close();
        return dataHomework;
    }
    public List<Upload> DataList_Upload(){
        List<Upload> dataUpload = new ArrayList<Upload>();
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            String[] rows = {UPLOAD_LESSON_NAME,UPLOAD_HOMEWORK_NAME,UPLOAD_STUDENT_NAME,UPLOAD_STUDENT_EMAIL,WORK};
            Cursor cursor = db.query(UPLOAD_TABLE, rows,null,null,null,null,null);
            while (cursor.moveToNext()){
                Upload s = new Upload(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
                dataUpload.add(s);
            }
        }catch (Exception e){
        }
        db.close();
        return dataUpload;
    }


    public void DeleteCourse(String course_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = COURSES_NAME +" = '"+ course_name + "'" ;
        db.delete(COURSE_TABLE,where,null);
        db.close();
    }

    public void UpdateCourse(String oldcoursename,String teacher_name, String course_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COURSE_TEACHER,teacher_name);
        values.put(COURSES_NAME,course_name);
        String where = COURSES_NAME +" = '"+ oldcoursename + "'" ;
        db.update(COURSE_TABLE,values,where,null);
        db.close();
    }
    public void UpdateAnnouncements(String oldcoursename,String course_name,String teacher_name, String text) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ANNOUNCEMENTS_COURSE,course_name);
        values.put(ANNOUNCEMENTS_TEACHER,teacher_name);
        values.put(ANNOUNCEMENTS_TEXT,text);
        String where = ANNOUNCEMENTS_TEXT +" = '"+ oldcoursename + "'" ;
        db.update(ANNOUNCEMENTS_TABLE,values,where,null);
        db.close();
    }
    public void DeleteAnnouncements(String announcement) {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = ANNOUNCEMENTS_TEXT +" = '"+ announcement + "'" ;
        db.delete(ANNOUNCEMENTS_TABLE,where,null);
        db.close();
    }

    public void UpdateHomework(String oldhomeworkname,String homeworkname,String explanation, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(HOMEWORK_NAME,homeworkname);
        values.put(EXPLANATION,explanation);
        values.put(DUE_DATE,date);
        String where = HOMEWORK_NAME +" = '"+ oldhomeworkname + "'" ;
        db.update(HOMEWORK_TABLE,values,where,null);
        db.close();
    }

    public void DeleteHomework(String homework_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = HOMEWORK_NAME +" = '"+ homework_name + "'" ;
        db.delete(HOMEWORK_TABLE,where,null);
        db.close();
    }

    public void UpdatePost(String old_post,String post) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(POST,post);
        String where = POST +" = '"+ old_post + "'" ;
        db.update(POST_TABLE,values,where,null);
        db.update(COMMENT_TABLE,values,where,null);
        db.close();
    }
    public void DeletePost(String course_name,String post) {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = POST +" = '"+ post + "'" + " AND "+ COURSE_NAME +" = '"+ course_name + "'";
        db.delete(POST_TABLE,where,null);
        db.close();
    }

    public void onUpgrade(SQLiteDatabase db, int eski, int yeni) {
        db.execSQL("drop table student");
        db.execSQL("drop table Teacher");
    }
}

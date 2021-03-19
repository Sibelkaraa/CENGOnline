package com.example.cengo;

public class Announcement {
    String course_name;
    String teacher_name;
    String text;

    public Announcement(String course_name, String teacher_name, String text) {
        this.course_name = course_name;
        this.teacher_name = teacher_name;
        this.text = text;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

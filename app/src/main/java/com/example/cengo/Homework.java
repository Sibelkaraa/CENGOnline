package com.example.cengo;

public class Homework {
    private String Lesson_name;
    private String hw_name;
    private String explanation;
    private String due_date;
    private int photo_id;

    public Homework(String lesson_name, String hw_name, String explanation, String due_date, int photo_id) {
        this.Lesson_name = lesson_name;
        this.hw_name = hw_name;
        this.explanation = explanation;
        this.due_date = due_date;
        this.photo_id = photo_id;
    }

    public int getPhoto_id() {
        return photo_id;
    }

    public void setPhoto_id(int photo_id) {
        this.photo_id = photo_id;
    }

    public String getLesson_name() {
        return Lesson_name;
    }

    public void setLesson_name(String lesson_name) {
        Lesson_name = lesson_name;
    }

    public String getHw_name() {
        return hw_name;
    }

    public void setHw_name(String hw_name) {
        this.hw_name = hw_name;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }
}

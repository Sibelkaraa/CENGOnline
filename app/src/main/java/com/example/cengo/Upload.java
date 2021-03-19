package com.example.cengo;

public class Upload {
    private String lesson_name;
    private String homework_name;
    private String student_name;
    private String student_mail;
    private String work;

    public Upload(String lesson_name, String homework_name, String student_name, String student_mail, String work) {
        this.lesson_name = lesson_name;
        this.homework_name = homework_name;
        this.student_name = student_name;
        this.student_mail = student_mail;
        this.work = work;
    }

    public String getLesson_name() {
        return lesson_name;
    }

    public void setLesson_name(String lesson_name) {
        this.lesson_name = lesson_name;
    }

    public String getHomework_name() {
        return homework_name;
    }

    public void setHomework_name(String homework_name) {
        this.homework_name = homework_name;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getStudent_mail() {
        return student_mail;
    }

    public void setStudent_mail(String student_mail) {
        this.student_mail = student_mail;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }
}

package com.example.cengo;

public class Post {
    private String course_name;
    private String  teacher_name;
    private String post;

    public Post(String course_name, String teacher_name, String post) {
        this.course_name = course_name;
        this.teacher_name = teacher_name;
        this.post = post;
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

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }
}

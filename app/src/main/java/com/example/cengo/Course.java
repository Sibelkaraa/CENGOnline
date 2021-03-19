package com.example.cengo;

public class Course {
    String TeacherName;
    String CourseName;


    public Course(String teacherName, String courseName) {
        TeacherName = teacherName;
        CourseName = courseName;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public String getTeacherName() {
        return TeacherName;
    }

    public void setTeacherName(String teacherName) {
        TeacherName = teacherName;
    }
}

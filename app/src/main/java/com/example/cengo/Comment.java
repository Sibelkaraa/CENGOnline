package com.example.cengo;

public class Comment {
    private String post;
    private String person_name;
    private String comment;

    public Comment(String post, String person_name, String comment) {
        this.post = post;
        this.person_name = person_name;
        this.comment = comment;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

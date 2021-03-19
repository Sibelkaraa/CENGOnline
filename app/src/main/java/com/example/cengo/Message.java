package com.example.cengo;

public class Message {

    String from_who;
    String to_who;
    String message_text;

    public Message(String from_who, String to_who, String message_text) {
        this.from_who = from_who;
        this.to_who = to_who;
        this.message_text = message_text;
    }

    public String getFrom_who() {
        return from_who;
    }

    public void setFrom_who(String from_who) {
        this.from_who = from_who;
    }

    public String getTo_who() {
        return to_who;
    }

    public void setTo_who(String to_who) {
        this.to_who = to_who;
    }

    public String getMessage_text() {
        return message_text;
    }

    public void setMessage_text(String message_text) {
        this.message_text = message_text;
    }


}

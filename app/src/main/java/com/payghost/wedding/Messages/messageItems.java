package com.payghost.wedding.Messages;

/**
 * Created by Payghost on 2/22/2018.
 */

public class messageItems {

    private String subject;
    private String message;
    private String time;
    private String id;

    public messageItems(String subject, String message, String time,String id) {
        this.subject = subject;
        this.message = message;
        this.time = time;
        this.id = id;
    }
    public messageItems(String subject, String message, String time) {
        this.subject = subject;
        this.message = message;
        this.time = time;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

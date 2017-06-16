package org.jamaica.pcog.mobile.inbox.model;

/**
 * Created by hisham on 9/6/2015.
 */
public class InboxModel {
    private String title;
    private String subject;
    private String description;
    private String time;
    private String date;


    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.title = subject;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }


    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
}

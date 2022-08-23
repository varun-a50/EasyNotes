package com.example.easynotes;


import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

public class Note implements Serializable {
    @Exclude private String id;

    String title,details;

    public Note(){}

    public Note(String title, String details) {
        this.title = title;
        this.details = details;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}

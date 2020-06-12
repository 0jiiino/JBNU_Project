package com.jbnu.project1;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

import static com.jbnu.project1.PostID.email;

public class Post {

    private String documentId;
    private String title;
    private String contents;
    private String email;
    @ServerTimestamp
    private Date date;

    public Post(String email,  String documentId, String title,String contents) {
        this.email=email;
        this.documentId = documentId;
        this.title = title;
        this.contents = contents;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }


    public String getDate(){
        return String.valueOf(date);
    }
    public void setDate(Date date){
        this.date = date;
    }
    @Override
    public String toString() {
        return "Post{" +
                "email='" + email + '\'' +
                "documentId='" + documentId + '\'' +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                '}';
    }
}

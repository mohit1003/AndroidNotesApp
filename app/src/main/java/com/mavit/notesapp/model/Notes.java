package com.mavit.notesapp.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "NotesTable")
public class Notes implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "Title")
    private String title;

    @ColumnInfo(name = "Date")
    private String date;

    @ColumnInfo(name = "SubTitle")
    private String subTitle;

    @ColumnInfo(name = "Note")
    private String note;

    @ColumnInfo(name = "PhotoURL")
    private String photoURL;

    @ColumnInfo(name = "WebLink")
    private String webLink;

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getWebLink() {
        return webLink;
    }

    public void setWebLink(String webLink) {
        this.webLink = webLink;
    }

    public Notes(String title, String note) {
//        super(title);
        this.title = title;
//        this.date = date;
//        this.subTitle = subTitle;
        this.note = note;
    }

    public Notes() {
//        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @NonNull
    @Override
    public String toString() {
        return title + ":" +date;
    }
}


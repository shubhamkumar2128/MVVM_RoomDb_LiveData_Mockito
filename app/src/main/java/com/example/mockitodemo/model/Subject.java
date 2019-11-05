package com.example.mockitodemo.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Subjects_data")
public class Subject {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String disc;

    public Subject(String title, String disc) {
        this.title = title;
        this.disc = disc;
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

    public String getDisc() {
        return disc;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }
















}

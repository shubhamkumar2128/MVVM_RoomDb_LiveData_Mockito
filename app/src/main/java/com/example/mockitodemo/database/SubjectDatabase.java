package com.example.mockitodemo.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mockitodemo.dao.SubjectDao;
import com.example.mockitodemo.model.Subject;

@Database(entities = Subject.class, version = 1)
public abstract class SubjectDatabase extends RoomDatabase {
    public static SubjectDatabase subjectDatabaseinstance;

    public abstract SubjectDao subjectDao();

    public static synchronized SubjectDatabase getInstance(Context context) {
        if (subjectDatabaseinstance == null) {
            subjectDatabaseinstance = Room.databaseBuilder(context.getApplicationContext(),
                    SubjectDatabase.class, "subject_db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return subjectDatabaseinstance;
    }



}

package com.example.mockitodemo.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mockitodemo.model.Subject;

import java.util.List;

@Dao
public interface SubjectDao {
    @Insert
    void insert(Subject note);

    @Update
    void update(Subject note);

    @Delete
    void delete(Subject note);

    @Query("DELETE FROM Subjects_data")
    void deleteAll();

    @Query("Select * from Subjects_data")
    LiveData<List<Subject>> getAllSubjects();
}

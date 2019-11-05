package com.example.mockitodemo.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mockitodemo.Repository.SubjectRepository;
import com.example.mockitodemo.model.Subject;

import java.util.List;

public class SubjectViewModel extends AndroidViewModel {

    private SubjectRepository subjectRepository;
    private LiveData<List<Subject>> allsub;

    public SubjectViewModel(@NonNull Application application) {
        super(application);
        subjectRepository = new SubjectRepository(application);
        allsub = subjectRepository.getAllSubject();

    }

    public void insert(Subject subject) {
        subjectRepository.insert(subject);
    }

    public void update(Subject subject) {
        subjectRepository.update(subject);
    }

    public void delete(Subject subject) {
        subjectRepository.delete(subject);
    }

    public void deleteAll() {
        subjectRepository.deleteAll();
    }

    public LiveData<List<Subject>> getAllSubject() {
        return allsub;
    }

}

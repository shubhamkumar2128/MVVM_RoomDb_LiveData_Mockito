package com.example.mockitodemo.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.mockitodemo.dao.SubjectDao;
import com.example.mockitodemo.database.SubjectDatabase;
import com.example.mockitodemo.model.Subject;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class SubjectRepository {
    private SubjectDao subjectDao;
    private LiveData<List<Subject>> allsub;


    public SubjectRepository(Application application) {
        SubjectDatabase subjectDatabase = SubjectDatabase.getInstance(application);
        subjectDao = subjectDatabase.subjectDao();
        allsub = subjectDao.getAllSubjects();

    }


    public void insert(Subject subject)  {
        new InsertAsyncTask(subjectDao).execute(subject);

    }

    public void update(Subject subject) {
        new UpdateAsyncTask(subjectDao).execute(subject);
    }

    public void delete(Subject subject) {
        new DeleteAsyncTask(subjectDao).execute(subject);
    }

    public void deleteAll() {
        new DeleteAllAsyncTask(subjectDao).execute();
    }

    public LiveData<List<Subject>> getAllSubject() {
        return allsub;
    }

    public static class InsertAsyncTask extends AsyncTask<Subject, Void, Void> {
        private SubjectDao subjectDao;

        public InsertAsyncTask(SubjectDao subjectDao) {
            this.subjectDao = subjectDao;
        }

        @Override
        protected Void doInBackground(Subject... subjects) {
            subjectDao.insert(subjects[0]);
            return null;

        }


    }

    public static class UpdateAsyncTask extends AsyncTask<Subject, Void, Void> {
        private SubjectDao subjectDao;

        public UpdateAsyncTask(SubjectDao subjectDao) {
            this.subjectDao = subjectDao;
        }

        @Override
        protected Void doInBackground(Subject... subjects) {
            subjectDao.update(subjects[0]);
            return null;
        }
    }

    public static class DeleteAsyncTask extends AsyncTask<Subject, Void, Void> {
        private SubjectDao subjectDao;

        public DeleteAsyncTask(SubjectDao subjectDao) {
            this.subjectDao = subjectDao;
        }

        @Override
        protected Void doInBackground(Subject... subjects) {
            subjectDao.delete(subjects[0]);
            return null;
        }
    }

    public static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private SubjectDao subjectDao;

        public DeleteAllAsyncTask(SubjectDao subjectDao) {
            this.subjectDao = subjectDao;
        }

        @Override
        protected Void doInBackground(Void... subjects) {
            subjectDao.deleteAll();
            return null;
        }
    }


}

package com.example.mockitodemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mockitodemo.model.Subject;
import com.example.mockitodemo.viewmodel.SubjectViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int EDIT_REQUEST_CODE = 2;
    private SubjectViewModel subjectViewModel;
    private Button addbtn, deleteallbtn;
    public static final int ADD_SUBJECT_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        addbtn = findViewById(R.id.addnewbtn);
        deleteallbtn = findViewById(R.id.deleteallbtn);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        final SubjectAdapter subjectAdapter = new SubjectAdapter();
        recyclerView.setAdapter(subjectAdapter);
        subjectViewModel = ViewModelProviders.of(this).get(SubjectViewModel.class);
        subjectViewModel.getAllSubject().observe(this, new Observer<List<Subject>>() {
            @Override
            public void onChanged(List<Subject> subjects) {
                subjectAdapter.setSubjects(subjects);
            }
        });

        deleteallbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subjectViewModel.deleteAll();
                Toast.makeText(MainActivity.this, "All subject deleted..", Toast.LENGTH_SHORT).show();
            }
        });
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, AddEditSubject.class), ADD_SUBJECT_REQUEST);
            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                subjectViewModel.delete(subjectAdapter.getSujectPosition(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Subject Deleted..", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        subjectAdapter.setOnItemClickListner(new SubjectAdapter.onItemClickListner() {
            @Override
            public void onClick(Subject subject) {
                Intent intent = new Intent(MainActivity.this, AddEditSubject.class);
                intent.putExtra(AddEditSubject.EXTRA_ID, subject.getId());
                intent.putExtra(AddEditSubject.EXTRA_TITLE, subject.getTitle());
                intent.putExtra(AddEditSubject.EXTRA_DISC, subject.getDisc());
                startActivityForResult(intent, EDIT_REQUEST_CODE);

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK == resultCode && requestCode == ADD_SUBJECT_REQUEST) {
            String title = data.getStringExtra(AddEditSubject.EXTRA_TITLE);
            String disc = data.getStringExtra(AddEditSubject.EXTRA_DISC);
            Subject subject = new Subject(title, disc);
            subjectViewModel.insert(subject);


        } else if (RESULT_OK == resultCode && requestCode == EDIT_REQUEST_CODE) {
            int id = data.getIntExtra(AddEditSubject.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Subject can't updated", Toast.LENGTH_SHORT).show();
                return;
            }

            String title = data.getStringExtra(AddEditSubject.EXTRA_TITLE);
            String disc = data.getStringExtra(AddEditSubject.EXTRA_DISC);
            Subject subject = new Subject(title, disc);
            subject.setId(id);
            subjectViewModel.update(subject);
        } else
            Toast.makeText(this, "Data not saved", Toast.LENGTH_SHORT).show();

    }


}

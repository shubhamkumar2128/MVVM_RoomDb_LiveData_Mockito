package com.example.mockitodemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddEditSubject extends AppCompatActivity {
    public static final String EXTRA_ID = "com.example.mockitodeno.EXTRA_ID";
    public static final String EXTRA_TITLE = "com.example.mockitodeno.EXTRA_TITLE";
    public static final String EXTRA_DISC = "com.example.mockitodeno.EXTRA_DISC";

    private EditText title, disc;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);
        save = findViewById(R.id.savebtn);
        title = findViewById(R.id.ettitle);
        disc = findViewById(R.id.etdisc);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSubject();
            }
        });

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Subject");
            title.setText(intent.getStringExtra(EXTRA_TITLE));
            disc.setText(intent.getStringExtra(EXTRA_DISC));
        } else
            setTitle("Add Subject");

    }


    private void saveSubject() {
        String title = this.title.getText().toString();
        String disc = this.disc.getText().toString();
        if (title.trim().isEmpty() || disc.trim().isEmpty()) {
            Toast.makeText(this, "Fill all fields..", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_TITLE, title);
        intent.putExtra(EXTRA_DISC, disc);

        int id=getIntent().getIntExtra(EXTRA_ID,-1);
        if(id!=-1)
        {
            intent.putExtra(EXTRA_ID,id);
        }
        setResult(RESULT_OK, intent);
        finish();

    }

}

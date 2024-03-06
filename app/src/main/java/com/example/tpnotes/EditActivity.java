package com.example.tpnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    Note note;
    EditText edTitle, edBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        edTitle = findViewById(R.id.edTitle);
        edBody = findViewById(R.id.edBody);

        note = getIntent().getParcelableExtra("note");

        edTitle.setText(note.getTitle());
        edBody.setText((note.getBody()));
    }
}
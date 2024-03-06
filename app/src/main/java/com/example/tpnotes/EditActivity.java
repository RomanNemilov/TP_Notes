package com.example.tpnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import org.w3c.dom.Text;

public class EditActivity extends AppCompatActivity {

    DbHelper dbHelper;
    SQLiteDatabase db;
    ContentValues values;
    Note note;
    EditText edTitle, edBody;

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            save();
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count){

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        dbHelper = new DbHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();
        values = new ContentValues();

        edTitle = findViewById(R.id.edTitle);
        edBody = findViewById(R.id.edBody);

        Bundle params = getIntent().getExtras();
        note = params.getParcelable("note");
                edTitle.setText(note.getTitle());
        edBody.setText((note.getBody()));
        edTitle.addTextChangedListener(textWatcher);
        edBody.addTextChangedListener(textWatcher);
    }

    public void save(){
        values.put(DbHelper.NOTES_ID, note.getId());
        values.put(DbHelper.NOTES_TITLE, edTitle.getText().toString());
        values.put(DbHelper.NOTES_BODY, edBody.getText().toString());
        db.update(DbHelper.TABLE_NOTES, values, DbHelper.NOTES_ID + "=?" ,
                new String[]{String.valueOf(note.getId())});
    }
}
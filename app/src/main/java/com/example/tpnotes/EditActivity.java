package com.example.tpnotes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
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

    public void onBack(View view){
        finish();
    }

    public void onDelete(View view){
        if (edTitle.getText().toString().trim().isEmpty() &&
                edBody.getText().toString().trim().isEmpty()){
            deleteAndExit();
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogTheme);
        builder.setMessage(R.string.confirm_delete_message)
                .setTitle(R.string.confirm_delete_title)
                .setPositiveButton(R.string.confirm, (dialog, id) -> {

                })
                .setNegativeButton(R.string.cancel, (dialog, id) -> {
                    // CANCEL
                }).show();
    }

    public void save(){
        values.put(DbHelper.NOTES_ID, note.getId());
        values.put(DbHelper.NOTES_TITLE, edTitle.getText().toString());
        values.put(DbHelper.NOTES_BODY, edBody.getText().toString());
        db.update(DbHelper.TABLE_NOTES, values, DbHelper.NOTES_ID + "=?" ,
                new String[]{String.valueOf(note.getId())});
    }
    public void deleteAndExit(){
        db.delete(DbHelper.TABLE_NOTES,DbHelper.NOTES_ID + "=?" ,
                new String[]{String.valueOf(note.getId())});
        finish();
    }
}
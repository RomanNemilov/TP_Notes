package com.example.tpnotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Note> notes = new ArrayList<>();
    MyAdapterNotes adapterNotes;
    RecyclerView rvNotes;
    DbHelper dbHelper;
    SQLiteDatabase db;
    Cursor cursor;
    String defaultTitle;

//    private ActivityResultLauncher<Intent> launcher =
//        registerForActivityResult( new ActivityResultContracts.StartActivityForResult(),
//            result -> {
//                if (result.getResultCode() == RESULT_OK){
//                    Intent data = result.getData();
//                    //Implement action
//                }
//            }
//        );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DbHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();

        rvNotes = findViewById(R.id.rvNotes);
        defaultTitle = getResources().getString(R.string.untitled);


        rvNotes.setLayoutManager(new LinearLayoutManager(this));

        updateNotes();
    }

    public void onFABAdd_click(View view){
        Note note = new Note(defaultTitle);
        ContentValues values = new ContentValues();
        values.put(DbHelper.NOTES_TITLE, note.getTitleForDB());
        values.put(DbHelper.NOTES_BODY, note.getBody());
        db.insert(DbHelper.TABLE_NOTES, null, values);
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra("note", note);
        startActivity(intent);
    }

    public void updateNotes(){
        getAllNotes();
        updateManager();
    }

    private void getAllNotes(){
        notes.clear();
        cursor = db.rawQuery("SELECT * FROM " + DbHelper.TABLE_NOTES, null);
        while (cursor.moveToNext()){
            notes.add(new Note(cursor.getString(1), defaultTitle, cursor.getString(2)));
        }
    }
    private void updateManager(){
        adapterNotes = new MyAdapterNotes(this, notes);
        rvNotes.setAdapter(adapterNotes);
    }
}
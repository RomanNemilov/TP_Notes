package com.example.tpnotes;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;

public class MyAdapterNotes  extends RecyclerView.Adapter<MyAdapterNotes.MyViewHolderNotes> {

    private Context context;
    private ArrayList<Note> notes;
    private String defaultTitle;

    public MyAdapterNotes(Context context, ArrayList<Note> notes) {
        this.context = context;
        this.notes = notes;
        Collections.reverse(this.notes); // Меняет порядок заметок на обратный
        defaultTitle = ((MainActivity)context).defaultTitle;
    }

    @NonNull
    @Override
    public MyViewHolderNotes onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.note_layout, parent, false);
        return new MyViewHolderNotes(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderNotes holder, int position) {
        Note note = notes.get(position);
        holder.id = note.getId();
        holder.tvTitle.setText(note.getTitleOrDefault(defaultTitle));
        holder.tvBody.setText(note.getBody());

        holder.layout.setOnClickListener(view -> {
            Intent intent = new Intent(context.getApplicationContext(), EditActivity.class);
            intent.putExtra("note", note);
            startActivity(context, intent, null);
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class MyViewHolderNotes extends RecyclerView.ViewHolder {
        long id;
        public TextView tvTitle, tvBody;
        ConstraintLayout layout;

        public MyViewHolderNotes(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvBody = itemView.findViewById(R.id.tvBody);
            layout = itemView.findViewById(R.id.constrainedLayout);
        }
    }
}

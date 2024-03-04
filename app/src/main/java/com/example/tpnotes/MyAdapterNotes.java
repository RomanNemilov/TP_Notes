package com.example.tpnotes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapterNotes  extends RecyclerView.Adapter<MyAdapterNotes.MyViewHolderNotes> {

    private Context context;
    private ArrayList<Note> notes;

    public MyAdapterNotes(Context context, ArrayList<Note> notes) {
        this.context = context;
        this.notes = notes;
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
        holder.tvHeader.setText(note.getHeader());
        holder.tvBody.setText(note.getBody());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class MyViewHolderNotes extends RecyclerView.ViewHolder {
        int id;
        public TextView tvHeader, tvBody;

        public MyViewHolderNotes(@NonNull View itemView) {
            super(itemView);
            tvHeader = itemView.findViewById(R.id.tvHeader);
            tvBody = itemView.findViewById(R.id.tvBody);
        }
    }
}

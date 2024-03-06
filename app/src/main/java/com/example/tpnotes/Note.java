package com.example.tpnotes;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Note implements Parcelable {
    private int id;
    private String title = "";
    private String defaultTitle;
    private String body = "";
    public Note(String defaultTitle) {
        this.defaultTitle = defaultTitle;
    }
    public Note(String header, String defaultTitle, String body) {
        this.title = header;
        this.defaultTitle = defaultTitle;
        this.body = body;
    }


    protected Note(Parcel in) {
        id = in.readInt();
        title = in.readString();
        defaultTitle = in.readString();
        body = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(defaultTitle);
        parcel.writeString(body);
    }
    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getTitleForDB() {
        if (title.trim().isEmpty()) return defaultTitle;
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


}

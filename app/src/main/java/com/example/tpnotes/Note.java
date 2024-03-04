package com.example.tpnotes;

public class Note {
    private int id;
    private String header = "Untitled";
    private String body = "";
    public Note() { }
    public Note(String header, String body) {
        this.header = header;
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}

package com.example.smaato.restservice;

public class SmaatoAccept{
    private final long id;
    private final String content;

    public SmaatoAccept(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}

package com.dev7.oldster.adsnativerecyclerview;

/**
 * Created by Old'ster on 2/27/2017.
 */

public class CommentData {
    private String name;
    private String date;
    private String conntent;

    public CommentData(String name, String date, String conntent) {
        this.name = name;
        this.date = date;
        this.conntent = conntent;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getConntent() {
        return conntent;
    }
}

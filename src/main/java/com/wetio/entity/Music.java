package com.wetio.entity;

public class Music {

    private int top;
    private String singer;
    private String name;
    private String album;
    private String url;

    public int getTop() { return top; }

    public void setTop(int top) { this.top = top; }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

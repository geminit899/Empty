package com.wetio.entity;

/**
 * @author Geminit
 * @create 2018-1-22
 */
public class Novel {

    private int id;
    private String name;
    private String latestChapter;
    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLatestChapter() {
        return latestChapter;
    }

    public void setLatestChapter(String latestChapter) {
        this.latestChapter = latestChapter;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

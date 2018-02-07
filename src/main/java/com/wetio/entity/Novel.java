package com.wetio.entity;

import java.util.Date;

/**
 * @author Geminit
 * @create 2018-1-22
 */
public class Novel {

    private String name;
    private String author;
    private String latestChapter;
    private String url;
    private Date beginTime;

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }
}

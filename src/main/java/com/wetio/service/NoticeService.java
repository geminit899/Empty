package com.wetio.service;

import com.wetio.entity.Notice;

import java.util.List;

/**
 * @author Geminit
 * @create 2018-2-7
 */

public interface NoticeService {

    public List<Notice> getNotice();

    public Notice getNoticeById(int id);

    public void deleteNoticeById(int id);

    public void deleteAllNotice();

    public void insertNotice(Notice notice);

}

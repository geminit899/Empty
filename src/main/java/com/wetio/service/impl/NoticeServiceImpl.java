package com.wetio.service.impl;

import com.wetio.dao.NoticeDao;
import com.wetio.entity.Notice;
import com.wetio.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Geminit
 * @create 2018-2-7
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeDao noticeDao;

    public List<Notice> getNotice() { return noticeDao.getNotice(); }

    public Notice getNoticeById(int id) { return noticeDao.getNoticeById(id); }

    public void deleteNoticeById(int id) { noticeDao.deleteNoticeById(id); }

    public void deleteAllNotice() { noticeDao.deleteAllNotice(); }

    public void insertNotice(Notice notice){
        noticeDao.insertNotice(notice.getTitle(), notice.getContent(), notice.getTime(), notice.getUrl());
    }

}

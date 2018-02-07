package com.wetio.dao;

import com.wetio.entity.Notice;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author Geminit
 * @create 2018-2-7
 */

@Repository
public interface NoticeDao {

    public List<Notice> getNotice();

    public Notice getNoticeById(int id);

    public void deleteNoticeById(int id);

    public void deleteAllNotice();

    public void insertNotice(@Param("title")String title, @Param("content")String content,
                             @Param("time")Date time, @Param("url")String url);

}

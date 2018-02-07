package com.wetio.dao;

import com.wetio.entity.Novel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author Geminit
 * @create 2018-2-7
 */

@Repository
public interface NovelDao {

    public List<Novel> getNovel();

    public List<Novel> getUnfinishedNovel();

    public Novel getNovelByName(String name);

    public void updateLatestChapterByName(@Param("name")String name, @Param("latestChapter")String latestChapter);

    public void deleteNovelByName(String name);

    public void deleteAllNovel();

    public void insertNovel(@Param("name")String name, @Param("author")String author,
                            @Param("latestChapter")String latestChapter, @Param("url")String url,
                            @Param("beginTime")Date beginTime);

}

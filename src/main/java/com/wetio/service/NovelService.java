package com.wetio.service;


import com.wetio.entity.Novel;

import java.util.Date;
import java.util.List;

/**
 * @author Geminit
 * @create 2018-1-22
 */

public interface NovelService {

    public List<Novel> getNovel();

    public List<Novel> getUnfinishedNovel();

    public Novel getNovelByName(String name);

    public void updateLatestChapterByName(String name, String latestChapter);

    public void deleteNovelByName(String name);

    public void deleteAllNovel();

    public void insertNovel(Novel novel);

}

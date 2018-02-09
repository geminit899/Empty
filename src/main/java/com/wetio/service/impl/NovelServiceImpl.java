package com.wetio.service.impl;

import com.wetio.dao.NovelDao;
import com.wetio.entity.Novel;
import com.wetio.service.NovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author Geminit
 * @create 2018-1-22
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class NovelServiceImpl implements NovelService {

    @Autowired
    private NovelDao novelDao;

    public List<Novel> getNovel() { return novelDao.getNovel(); }

    public List<Novel> getUnfinishedNovel() { return novelDao.getUnfinishedNovel(); }

    public Novel getNovelByName(String name) { return novelDao.getNovelByName(name); }

    public void updateLatestChapterByName(String name, String latestChapter) {
        novelDao.updateLatestChapterByName(name, latestChapter);
    }

    public void deleteNovelByName(String name) { novelDao.deleteNovelByName(name); }

    public void deleteAllNovel() { novelDao.deleteAllNovel(); }

    public void insertNovel(Novel novel) {
        novelDao.insertNovel(novel.getName(), novel.getAuthor(), novel.getLatestChapter(),
                                novel.getUrl(), novel.getBeginTime(), novel.getIsFinished());
    }

}

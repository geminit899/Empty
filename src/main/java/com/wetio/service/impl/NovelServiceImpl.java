package com.wetio.service.impl;

import com.wetio.dao.NovelDao;
import com.wetio.entity.Novel;
import com.wetio.service.NovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<Novel> getNovels(){ return novelDao.getNovels(); }

}

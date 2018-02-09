package com.wetio.service.impl;

import com.wetio.dao.MusicDao;
import com.wetio.entity.Music;
import com.wetio.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Geminit
 * @create 2018-2-6
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MusicServiceImpl implements MusicService{

    @Autowired
    private MusicDao musicDao;

    public List<Music> getMusic() { return musicDao.getMusic(); }

    public Music getMusicByName(String name){ return musicDao.getMusicByName(name); }

    public void deleteMusicByName(String name) { musicDao.deleteMusicByName(name); }

    public void deleteAllMusic() { musicDao.deleteAllMusic(); }

    public void insertMusic(Music music) {
        musicDao.insertMusic(music.getTop(), music.getName(), music.getSinger(), music.getAlbum(),music.getUrl());
    }

}

package com.wetio.service;

import com.wetio.entity.Music;

import java.util.List;

/**
 * @author Geminit
 * @create 2018-2-6
 */

public interface MusicService {

    public List<Music> getMusic();

    public Music getMusicByName(String name);

    public void deleteMusicByName(String name);

    public void deleteAllMusic();

    public void insertMusic(Music music);

}

package com.wetio.dao;

import com.wetio.entity.Music;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Geminit
 * @create 2018-2-6
 */

@Repository
public interface MusicDao {

    public List<Music> getMusic();

    public Music getMusicByName(String name);

    public void deleteMusicByName(String name);

    public void deleteAllMusic();

    public void insertMusic(@Param("top")int top, @Param("name")String name,
                            @Param("singer")String singer, @Param("url")String url);

}

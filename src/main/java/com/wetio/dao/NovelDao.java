package com.wetio.dao;

import com.wetio.entity.Novel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Geminit
 * @create 2018-1-21
 */

@Repository
public interface NovelDao {

    public List<Novel> getNovels();

}

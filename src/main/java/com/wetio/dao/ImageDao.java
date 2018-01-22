package com.wetio.dao;

import com.wetio.entity.Image;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Geminit
 * @create 2018-1-21
 */

@Repository
public interface ImageDao {

    public List<Image> getIndexImages();

}

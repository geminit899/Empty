package com.wetio.service.impl;

import com.wetio.dao.ImageDao;
import com.wetio.entity.Image;
import com.wetio.service.ImageService;
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
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageDao imageDao;

    public List<Image> getIndexImages(){ return imageDao.getIndexImages(); }

}

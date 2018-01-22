package com.wetio.controller;

import com.wetio.entity.Image;
import com.wetio.entity.Notice;
import com.wetio.entity.Novel;
import com.wetio.service.ImageService;
import com.wetio.service.NovelService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Geminit
 * @create 2018-1-21
 */

@Controller
@RequestMapping("/wetio")
public class IndexController {

    //添加一个日志器
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    NovelService novelService;

    @Autowired
    ImageService imageService;

    //映射一个action
    @RequestMapping("/index")
    public String index(Model model, HttpServletRequest request){
        //输出日志文件
        logger.info("the index page");

        List<Novel> novels = novelService.getNovels();
        List<Image> images = imageService.getIndexImages();

        List<Map> imagesList = new ArrayList<Map>();
        List<Map> novelsList = new ArrayList<Map>();
        Map map;

        for(int i = 0; i<novels.size() && i<7; i++){
            map = new HashMap();
            map.put("name",novels.get(i).getName());
            map.put("latestChapter",novels.get(i).getLatestChapter().split("章")[1]);
            map.put("url",novels.get(i).getUrl());
            novelsList.add(map);
        }

        for(int i = 0; i<images.size(); i++){
            map = new HashMap();
            map.put("url",images.get(i).getUrl());
            imagesList.add(map);
        }

        model.addAttribute("novelsList", novelsList);
        model.addAttribute("imagesList", imagesList);

        return "index";
    }

    public List<Notice> getNotices() throws IOException {

        List<Notice> notices = new ArrayList<Notice>();

        String url = "http://www.cdut.edu.cn/xww/type/1000020104.html";

        return  notices;

    }

}

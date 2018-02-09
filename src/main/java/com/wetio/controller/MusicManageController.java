package com.wetio.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wetio.entity.Music;
import com.wetio.service.MusicService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Geminit
 * @create 2018-2-9
 */

@Controller
@RequestMapping("/wetio/manage")
public class MusicManageController {

    //添加一个日志器
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    MusicService musicService;

    //映射一个action
    @RequestMapping("/music")
    public String musicManage(Model model, HttpServletRequest request) throws Exception {

        int toPage;

        if (request.getParameter("toPage") == null)
            toPage = 1;
        else
            toPage = Integer.parseInt(request.getParameter("toPage"));

        List<Music> musicList = musicService.getMusic();

        int musicNum = musicList.size();
        int pageNum = 1;

        if( musicNum%10 == 0 )
            pageNum = musicNum/10;
        else
            pageNum = musicNum/10 + 1;

        if( toPage<pageNum )
            musicList = musicList.subList((toPage-1)*10,toPage*10);
        else
            musicList = musicList.subList((toPage-1)*10,musicNum);

        model.addAttribute("currentPage", toPage);
        model.addAttribute("musicList", musicList);
        model.addAttribute("pageNum", pageNum);

        return "musicManage";
    }
}

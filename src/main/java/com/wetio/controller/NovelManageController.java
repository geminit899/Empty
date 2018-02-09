package com.wetio.controller;

import com.wetio.entity.Novel;
import com.wetio.service.NovelService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Geminit
 * @create 2018-2-9
 */

@Controller
@RequestMapping("/wetio/manage")
public class NovelManageController {

    //添加一个日志器
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    NovelService novelService;

    //映射一个action
    @RequestMapping("/novel")
    public String novelManage(Model model, HttpServletRequest request) throws Exception {

        List<Novel> novelList = novelService.getNovel();

        int novelNum = novelList.size();
        int pageNum = 1;

        if( novelNum%10 == 0 )
            pageNum = novelNum/10;
        else
            pageNum = novelNum/10 + 1;



        model.addAttribute("novelList", novelList);
        model.addAttribute("novelNum", novelNum);
        model.addAttribute("pageNum", pageNum);

        return "novelManage";
    }

}

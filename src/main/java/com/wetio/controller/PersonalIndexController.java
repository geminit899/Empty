package com.wetio.controller;

import com.wetio.service.ImageService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Geminit
 * @create 2018-2-1
 */

@Controller
@RequestMapping("/wetio/manage")
public class PersonalIndexController {

    //添加一个日志器
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    ImageService imageService;

    //映射一个action
    @RequestMapping("/personalIndex")
    public String index(Model model, HttpServletRequest request) throws Exception {

        //String user  = request.getSession().getAttribute("user").toString();


        model.addAttribute("contributions", "");

        return "personalIndex";
    }

}

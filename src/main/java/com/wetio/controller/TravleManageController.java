package com.wetio.controller;

import com.wetio.entity.Travle;
import com.wetio.service.TravleService;
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
 * @create 2018-2-11
 */

@Controller
@RequestMapping("/wetio/manage")
public class TravleManageController {

    //添加一个日志器
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    TravleService travleService;

    //映射一个action
    @RequestMapping("/travle")
    public String travleManage(Model model, HttpServletRequest request) {

        int toPage;

        if (request.getParameter("toPage") == null)
            toPage = 1;
        else
            toPage = Integer.parseInt(request.getParameter("toPage"));

        List<Travle> travleList = travleService.getTravle();

        int travleNum = travleList.size();
        int pageNum = 1;

        if( travleNum%10 == 0 )
            pageNum = travleNum/10;
        else
            pageNum = travleNum/10 + 1;

        if( toPage<pageNum )
            travleList = travleList.subList((toPage-1)*10,toPage*10);
        else
            travleList = travleList.subList((toPage-1)*10,travleNum);

        model.addAttribute("currentPage", toPage);
        model.addAttribute("travleList", travleList);
        model.addAttribute("pageNum", pageNum);

        return "travleManage";
    }

    //映射一个action
    @RequestMapping("/travle/add")
    @ResponseBody
    public String login(HttpServletRequest request, HttpServletResponse response) {

        Travle travle = new Travle();

        if ( request.getParameter("company") == null )
            travle.setCompany("");
        else
            travle.setCompany(request.getParameter("company"));

        String way = request.getParameter("destination");
        travle.setStartCity(way.split("-")[0]);
        travle.setDestinations(way.split("-").length-1);
        travle.setWay(way);

        if ( request.getParameter("beginTime") == null )
            travle.setBeginTime("");
        travle.setBeginTime(request.getParameter("beginTime"));

        try {
            travleService.insertTravle(travle);
        } catch (Exception e){
            return "error";
        }

        return "success";
    }

}

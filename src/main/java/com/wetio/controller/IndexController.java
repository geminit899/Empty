package com.wetio.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wetio.entity.Image;
import com.wetio.entity.Music;
import com.wetio.entity.Notice;
import com.wetio.entity.Novel;
import com.wetio.service.ImageService;
import com.wetio.service.MusicService;
import com.wetio.service.NoticeService;
import com.wetio.service.NovelService;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @Autowired
    MusicService musicService;

    @Autowired
    NoticeService noticeService;

    //映射一个action
    @RequestMapping("/index")
    public String index(Model model, HttpServletRequest request) throws Exception {
        //输出日志文件
        logger.info("the index page");

        //从数据库获取novel和image
        List<Novel> novels = novelService.getNovels();
        List<Image> images = imageService.getIndexImages();

        List<Map> imagesList = new ArrayList<>();
        List<Map> novelsList = new ArrayList<>();
        Map map;

        //获取novels
        for (int i = 0; i < novels.size() && i < 8; i++) {
            map = new HashMap();
            map.put("name", novels.get(i).getName());
            map.put("latestChapter", novels.get(i).getLatestChapter().split("章")[1]);
            map.put("url", novels.get(i).getUrl());
            novelsList.add(map);
        }

        //获取主页的image
        for (int i = 0; i < images.size() && i<10; i++) {
            map = new HashMap();
            map.put("url", images.get(i).getUrl());
            imagesList.add(map);
        }

        //获取notice
        List<Notice> notices = noticeService.getNotice().subList(0, 8);

        //获取musics
        List<Music> musics = musicService.getMusic().subList(0, 8);

        //获取github
        String github[] = simulateLogin("geminit@163.com", "IamI123..!!");

        model.addAttribute("novelsList", novelsList);
        model.addAttribute("imagesList", imagesList);
        model.addAttribute("notices", notices);
        model.addAttribute("musics", musics);
        model.addAttribute("githubImageURL", github[0]);
        model.addAttribute("contributions", github[1]);

        return "index";
    }

    //映射一个action
    @RequestMapping("/index/login")
    @ResponseBody
    public String login(HttpServletRequest request, HttpServletResponse response) {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if( !username.equals("geminit") || !password.equals("hack") )
            return "error";

        if( request.getSession().getAttribute("user") != null )
            request.getSession().removeAttribute("user");

        request.getSession().setAttribute("user", username);

        return username;
    }


    public String[] simulateLogin(String userName, String pwd) throws Exception {
         /*
         * 第一次请求
         * grab login form page first
         * 获取登陆提交的表单信息，及修改其提交data数据（login，password）
         */
        // get the response, which we will post to the action URL(rs.cookies())
        Connection con = Jsoup.connect("https://github.com/login");  // 获取connection
        // 配置模拟浏览器
        con.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0");
        Connection.Response rs = con.execute();                // 获取响应
        Document d1 = Jsoup.parse(rs.body());       // 转换为Dom树
        List<Element> eleList = d1.select("form");  // 获取提交form表单，可以通过查看页面源码代码得知

        // 获取cooking和表单属性
        // lets make data map containing all the parameters and its values found in the form
        Map<String, String> datas = new HashMap<>();
        for (Element e : eleList.get(0).getAllElements()) {
            // 设置用户名
            if (e.attr("name").equals("login")) {
                e.attr("value", userName);
            }
            // 设置用户密码
            if (e.attr("name").equals("password")) {
                e.attr("value", pwd);
            }
            // 排除空值表单属性
            if (e.attr("name").length() > 0) {
                datas.put(e.attr("name"), e.attr("value"));
            }
        }

        /*
         * 第二次请求，以get方式提交表单数据以及cookie信息
         */
        Connection con2 = Jsoup.connect("https://github.com/geminit899");
        con2.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0");
        // 设置cookie和post上面的map数据
        Connection.Response index = con2.ignoreContentType(true).followRedirects(true).method(Connection.Method.GET)
                .data(datas).cookies(rs.cookies()).execute();


        String githubImageURL = "";
        String contributions = "";

        try {
            Document githubDocument = Jsoup.parse(index.body());
            Element mainContent = githubDocument.getElementById("js-pjax-container")
                    .select("[class=container-lg clearfix px-3 mt-4]").get(0);
            Element image = mainContent.select("[itemprop=image]").get(0);
            githubImageURL = image.getElementsByTag("img").get(0).attr("src");
            Element contributionsDiv = mainContent.select("[class=col-9 float-left pl-2]").get(0)
                    .select("[class=position-relative]").get(0)
                    .select("[class=mt-4]").get(0)
                    .select("[class=js-contribution-graph]").get(0)
                    .select("[class=mb-5 border border-gray-dark rounded-1 py-2]").get(0);
            contributions = contributionsDiv.getElementsByTag("div").get(1).toString();
        } catch (Exception e) {
            contributions = "<center><h1>Can not link to github.com</h1></center>";
        }

        String gitHub[] = {githubImageURL, contributions};

        return gitHub;
    }
}
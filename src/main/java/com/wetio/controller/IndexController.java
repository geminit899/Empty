package com.wetio.controller;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.wetio.entity.Image;
import com.wetio.entity.Music;
import com.wetio.entity.Notice;
import com.wetio.entity.Novel;
import com.wetio.service.ImageService;
import com.wetio.service.NovelService;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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
import java.util.logging.Level;

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

        //从数据库获取novel和image
        List<Novel> novels = novelService.getNovels();
        List<Image> images = imageService.getIndexImages();

        List<Map> imagesList = new ArrayList<>();
        List<Map> novelsList = new ArrayList<>();
        Map map;

        for(int i = 0; i<novels.size() && i<8; i++){
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

        //获取notice
        List<Notice> notices = new ArrayList<>();
//        try{
//            notices = getNotices();
//        }catch (IOException ie){
//            System.out.println("IOException");
//        }

        List<Music> musics = new ArrayList<>();
        try {
            musics = getMusics();
        }catch (IOException ie){
            System.out.println("IOException");
        }

        model.addAttribute("novelsList", novelsList);
        model.addAttribute("imagesList", imagesList);
        model.addAttribute("notices", notices);
        model.addAttribute("musics", musics);

        return "index";
    }

    public List<Notice> getNotices() throws IOException {

        List<Notice> notices = new ArrayList<>();
        Notice notice;

        String url = "http://www.cdut.edu.cn/xww/type/1000020104.html";

        WebClient webClient = new WebClient(BrowserVersion.FIREFOX_45);//设置浏览器的User-Agent
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());//设置支持AJAX
        webClient.setJavaScriptTimeout(10000);//设置JS执行的超时时间
        webClient.waitForBackgroundJavaScript(10000);//设置JS后台等待执行时间
        webClient.getOptions().setThrowExceptionOnScriptError(false);//当JS执行出错的时候是否抛出异常
        webClient.getOptions().setTimeout(100000);//设置“浏览器”的请求超时时间
        webClient.getOptions().setCssEnabled(false);//是否启用CSS
        webClient.getOptions().setJavaScriptEnabled(true); //很重要，启用JS

        //不显示异常警告
        LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log",    "org.apache.commons.logging.impl.NoOpLog");
        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
        java.util.logging.Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.OFF);

        HtmlPage page = webClient.getPage(url);//获取初始页面,第一个page对象
        String hrefValue = "javascript:window.onload = new function(){doPage();}";
        ScriptResult executeJS = page.executeJavaScript(hrefValue);//执行js方法

        HtmlPage webContent = (HtmlPage) executeJS.getNewPage();//获得执行后的新page对象
        webClient.waitForBackgroundJavaScript(10000);

        Document document = Jsoup.parse(webContent.asXml());//将新的page页面交给Jsoup
        Element showhtml = document.getElementById("showhtml");//获得showhtml中首页前32个元素
        Elements lis = showhtml.getElementsByTag("li");//获取ul中所有li标签

        for (int i=0; i<8; i++){
            Element li = lis.get(i);//获取当前的li标签
            Element span = li.select("[class=time]").get(0);//获取当前含有时间的标签span
            String time = span.text();//将标签span内的发布时间取出

            //获取当前通知的网址
            String href = li.select("a").get(0).attr("href");

            //获取标题
            String title = li.select("a").get(0).attr("xwbt");

            if( title.length()>25 ) {
                title = title.substring(0,23) + "...";
            }

            notice = new Notice();
            notice.setTitle(title);
            notice.setTime(time);
            notice.setUrl(href);
            notices.add(notice);
        }

        page.cleanUp();
        webContent.cleanUp();

        return  notices;
    }

    public List<Music> getMusics() throws IOException{

        List<Music> musics = new ArrayList<>();
        Music music;

//        String url = "http://music.163.com/user/songs/rank?id=258625371";
//
//        Document doc = Jsoup.connect(url).header("Referer", "http://music.163.com/")
//                                         .header("Host", "music.163.com").get();
//        Element div = doc.getElementById("song-list-pre-cache");
//        Element ul = div.getElementsByTag("ul").get(0);
//        Elements as = ul.getElementsByTag("a");
//
//        for (int i=0; i<as.size() && i<8; i++){
//
//            String href = "http://music.163.com" + as.get(i).attr("href");
//
//            try{
//                doc = Jsoup.connect(href).get();
//            }
//            catch(Exception e){
//                e.printStackTrace();
//            };
//
//            Element element = doc.getElementsByTag("title").get(0);
//            String title = element.text();
//
//            String name = title.split("-")[0];
//            String author = title.split("-")[1];
//
//            music = new Music();
//            music.setAuthor(author);
//            music.setName(name);
//            music.setUrl(href);
//            musics.add(music);
//        }



        return musics;

    }

}

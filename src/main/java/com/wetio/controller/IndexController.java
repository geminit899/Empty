package com.wetio.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.regex.Pattern;

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
    public String index(Model model, HttpServletRequest request) {
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
        for (int i = 0; i < images.size(); i++) {
            map = new HashMap();
            map.put("url", images.get(i).getUrl());
            imagesList.add(map);
        }

        //获取notice
        List<Notice> notices = new ArrayList<>();
//        try{
//            notices = getNotices();
//        }catch (IOException ie){
//            System.out.println("IOException");
//        }

        //获取musics
        List<Music> musics = new ArrayList<>();
        try {
            musics = getMusics();
        } catch (IOException ie) {
            System.out.println("IOException");
        }

        //获取github
        try {
            String github = simulateLogin("geminit@163.com", "IamI123..!!");
        } catch (Exception e) {
            System.out.println("Fetching github failed!");
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
        LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
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

        for (int i = 0; i < 8; i++) {
            Element li = lis.get(i);//获取当前的li标签
            Element span = li.select("[class=time]").get(0);//获取当前含有时间的标签span
            String time = span.text();//将标签span内的发布时间取出

            //获取当前通知的网址
            String href = li.select("a").get(0).attr("href");

            //获取标题
            String title = li.select("a").get(0).attr("xwbt");

            if (title.length() > 25) {
                title = title.substring(0, 23) + "...";
            }

            notice = new Notice();
            notice.setTitle(title);
            notice.setTime(time);
            notice.setUrl(href);
            notices.add(notice);
        }

        page.cleanUp();
        webContent.cleanUp();

        return notices;
    }

    public List<Music> getMusics() throws IOException {

        List<Music> musics = new ArrayList<>();
        Music music;

        URL url = new URL("http://music.163.com/weapi/v1/play/record?csrf_token=");
        ;
        String parma = "params=3DPzrzItjAbzYlm9ir446U0zrvtB3oXN8R8W%2FM0XV9cAl88fk9XGUtPeqBESRfNlAwW6Kg2AZw1Cu9STZreSAp2OYafeTcuE1LS1akwCTYQLDyHflBsniY60bXNEW5a4Zqiq%2B7jqfd%2FlxOQoGwQpV07VOTAe18%2BVIYpw%2BnNnUM%2FshuFd9Rn%2FwCSGcLug8qWm&encSecKey=aeb7714d007240d8811b44b1835dfd5eeac48446e2bfe6cd2c9c6f9d5e1cd2e644b4b9f822edffadac54d3c9e6d20c220493e99c09c6ea7328c72411af360ac1390c27486f225b434a972900c7983562cc8e8d1745abad7b6d53ba51c0cfee7d8e7c97cb88000dff8f1af908a6de4eb7ad6bcc35fa1e5f3708fce00323dcc6bc";
        String referer = "http://music.163.com/user/songs/rank?id=258625371";

        JSONObject allData = null;
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();  // 打开连接
            // 设置连接输出流为true,默认false (post 请求是以流的方式隐式的传递参数)
            connection.setDoOutput(true);
            // 设置连接输入流为true
            connection.setDoInput(true);
            // 设置请求方式为post
            connection.setRequestMethod("POST");
            // post请求缓存设为false
            connection.setUseCaches(false);
            // 设置该HttpURLConnection实例是否自动执行重定向
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Referer", referer);
            connection.connect();
            DataOutputStream dataout = new DataOutputStream(connection.getOutputStream());
            dataout.writeBytes(parma);
            // 输出完成后刷新并关闭流
            dataout.flush();
            dataout.close(); // 重要且易忽略步骤 (关闭流,切记!)

            // 连接发起请求,处理服务器响应  (从连接获取到输入流并包装为bufferedReader)
            BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            StringBuilder sb = new StringBuilder(); // 用来存储响应数据
            // 循环读取流,若不到结尾处
            while ((line = bf.readLine()) != null) {
                //sb.append(bf.readLine());
                sb.append(line).append(System.getProperty("line.separator"));
            }
            bf.close();    // 重要且易忽略步骤 (关闭流,切记!)
            connection.disconnect(); // 销毁连接
            allData = JSONObject.parseObject(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONArray weekData = JSONArray.parseArray(allData.getString("weekData"));

        for (int i = 0; i < weekData.size() && i < 8; i++) {
            music = new Music();
            JSONObject song = JSONObject.parseObject(((JSONObject) weekData.get(i)).getString("song"));
            String ar = song.getString("ar");
            JSONArray singerArray = JSONArray.parseArray(ar);

            if (singerArray.size() > 1) {
                String singerString = "";
                for (int j = 0; j < singerArray.size(); j++) {
                    if (j != 0)
                        singerString += "、";
                    singerString += ((JSONObject) singerArray.get(j)).getString("name");
                }
            } else
                music.setAuthor(((JSONObject) singerArray.get(0)).getString("name"));

            music.setName(song.getString("name"));
            music.setUrl("http://music.163.com/#/song?id=" + song.getString("id"));
            musics.add(music);
        }

        return musics;
    }

    public String simulateLogin(String userName, String pwd) throws Exception {
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
         * 第二次请求，以post方式提交表单数据以及cookie信息
         */
        Connection con2 = Jsoup.connect("https://github.com/session");
        con2.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0");
        // 设置cookie和post上面的map数据
        Connection.Response login = con2.ignoreContentType(true).followRedirects(true).method(Connection.Method.POST)
                .data(datas).cookies(rs.cookies()).execute();

        /*
         * 第三次请求，以get方式提交表单数据以及cookie信息
         */
        Connection con3 = Jsoup.connect("https://github.com/geminit899");
        con2.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0");
        // 设置cookie和post上面的map数据
        Connection.Response index = con3.ignoreContentType(true).followRedirects(true).method(Connection.Method.GET)
                .data(datas).cookies(rs.cookies()).execute();

        // parse the document from response
        String body = index.body();

        return body;
    }
}
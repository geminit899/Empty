package com.wetio.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wetio.entity.Image;
import com.wetio.entity.Music;
import com.wetio.entity.Notice;
import com.wetio.entity.Novel;
import com.wetio.service.ImageService;
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
        List<Notice> notices = getNotices();

        //获取musics
        List<Music> musics = getMusics();

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

        return username + password;
    }


    public List<Notice> getNotices() throws IOException {

        List<Notice> notices = new ArrayList<>();
        Notice notice;

        String content = null;

        URL url = new URL("http://www.cdut.edu.cn/xww/dwr/call/plaincall/portalAjax.getNewsXml.dwr");
        String parma = "callCount=1&page=/xww/type/1000020104.html&httpSessionId=CA688021B747C7705E76BB56F444B9B7" +
                "&scriptSessionId=A1D314A51034B319F43931B8845BBE2A156&c0-scriptName=portalAjax&c0-methodName=getNewsXml" +
                "&c0-id=0&c0-param0=string:10000201&c0-param1=string:1000020104&c0-param2=string:news_" +
                "&c0-param3=number:32&c0-param4=number:1&c0-param5=null:null&c0-param6=null:null&batchId=8";
        String referer = "http://www.cdut.edu.cn/xww/type/1000020104.html";

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
            content = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Document document = Jsoup.parse(content);
        Elements guids = document.getElementsByTag("guid");

        for(int i=0; i<guids.size() && i<8; i++){
            notice = new Notice();
            notice.setUrl(guids.get(i).toString().split("\n")[1]);

            // 根据链接（字符串格式），生成一个URL对象
            URL noticeUrl = new URL(notice.getUrl());

            // 打开URL
            HttpURLConnection noticeConnection = (HttpURLConnection) noticeUrl.openConnection();

            // 得到输入流，即获得了网页的内容
            BufferedReader noticeReader = new BufferedReader(new InputStreamReader
                    (noticeConnection.getInputStream(), "GBK"));

            // 读取输入流的数据，并显示
            String noticePage = "";
            while (noticeReader.readLine() != null) {
                noticePage += noticeReader.readLine();
            }

            Document noticeDoc = Jsoup.parse(noticePage);
            int clickTime = Integer.parseInt(noticeDoc.getElementById("newsNum").text());
            String title = noticeDoc.select("[class=title]").get(0).text();
            notice.setTitle(title.split("来源")[0].length()>25?title.split("来源")[0].substring(0, 23) + "...":title.split("来源")[0]);
            notice.setContent(title.split("点击数量:" + clickTime)[1]);
            notice.setTime(noticeDoc.select("[class=pubtime]").get(0).text().split("：")[1]);
            notices.add(notice);
        }

        return notices;
    }


    public List<Music> getMusics() throws IOException {

        List<Music> musics = new ArrayList<>();
        Music music;

        URL url = new URL("http://music.163.com/weapi/v1/play/record?csrf_token=");
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
                music.setAuthor(singerString);
            } else
                music.setAuthor(((JSONObject) singerArray.get(0)).getString("name"));

            music.setName(song.getString("name"));
            music.setUrl("http://music.163.com/#/song?id=" + song.getString("id"));
            musics.add(music);
        }

        return musics;
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
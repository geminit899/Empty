package com.wetio.listener;

import com.wetio.entity.Music;
import com.wetio.entity.Notice;
import com.wetio.service.NoticeService;
import com.wetio.util.SentMail;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContextEvent;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Geminit
 * @create 2018-2-7
 */

public class NoticeSchedule {

    private NoticeService noticeService;
    private List<Notice> notices;

    public void noticeSchedule(ServletContextEvent sce){

        noticeService = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext())
                .getBean(NoticeService.class);

        try {
            getNotice();
        } catch (Exception e) {
            System.out.println("Error occured in getNotice().");
        }

        writeNotice();

    }

    public void getNotice() throws Exception{
        notices = new ArrayList<>();
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

        for(int i=0; i<guids.size(); i++){
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
            notice.setTitle(title.split("来源")[0]);
            notice.setContent(title.split("点击数量:" + clickTime)[1]);
            String time = noticeDoc.select("[class=pubtime]").get(0).text().split("：")[1];
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date date = sdf.parse(time);
            notice.setTime(date);
            notices.add(notice);
        }
    }

    public void writeNotice(){

        List<Notice> noticeInMysql = noticeService.getNotice();

        if( noticeInMysql.size() == 0 ) {
            for (int i = 0; i < notices.size(); i++) {
                noticeService.insertNotice(notices.get(i));
            }
            return;
        }

        Map<String, Notice> map = new HashMap<>(noticeInMysql.size());
        for(Notice notice : noticeInMysql)
            map.put(notice.getTime().toString(), notice);

        for(Notice notice : notices){
            if( map.get(notice.getTime().toString()) == null ){
                noticeService.insertNotice(notice);
                SentMail.sentMail(notice.getTitle(), notice.getContent() + "\n\n\n原网址:" + notice.getUrl());
            }
        }
    }
}

package com.wetio.listener;

import com.wetio.entity.Novel;
import com.wetio.service.NovelService;
import com.wetio.util.SentMail;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author Geminit
 * @create 2018-2-7
 */

public class NovelSchedule {

    private NovelService novelService;
    private List<Novel> novels;

    public void novelSchedule(ServletContextEvent sce){

        novelService = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext())
                .getBean(NovelService.class);
        novels = novelService.getUnfinishedNovel();

        try {
            getNovel();
        } catch (Exception e) {
            System.out.println("Error occured in getNovel().");
        }

    }

    public void getNovel() throws Exception {

        for(int i=0; i<novels.size(); i++){

            Novel novel = novels.get(i);

            Connection con = Jsoup.connect("http://www.biquge.com.tw/0_213/");  // 获取connectioni
            Connection.Response rs = con.execute();                // 获取响应

            Connection con2 = Jsoup.connect("http://www.biquge.com.tw/0_213/");
            // 设置cookie和post上面的map数据
            Connection.Response index = con2.ignoreContentType(true).followRedirects(true).method(Connection.Method.GET)
                    .cookies(rs.cookies()).execute();

            // 将bodyStream通过GBK解码
            BufferedReader bf = new BufferedReader(new InputStreamReader(index.bodyStream(), "GBK"));
            String line;
            StringBuilder sb = new StringBuilder(); // 用来存储响应数据
            // 循环读取流,若不到结尾处
            while ((line = bf.readLine()) != null) {
                sb.append(line).append(System.getProperty("line.separator"));
            }
            bf.close();    // 重要且易忽略步骤 (关闭流,切记!)

            Document document = Jsoup.parse(sb.toString());
            Elements dd = document.getElementsByTag("dd");

            Map<String, String> newNovels = new HashMap<>();
            List<String> names = new ArrayList<>();

            String latestChapter = novel.getLatestChapter();

            for(int j = dd.size()-1; j>=0; j--){
                String name = dd.get(j).text();
                if( latestChapter.equals(name) ){
                    break;
                }
                newNovels.put(dd.get(j).text(),dd.get(j).getElementsByTag("a").get(0).attr("href"));
                names.add(dd.get(j).text());
            }

            if( names.size() == 0 )
                return;

            for(int j=names.size()-1 ; j>=0; j--){

                con = Jsoup.connect("http://www.biquge.com.tw" + newNovels.get( names.get(j) ));  // 获取connectioni
                rs = con.execute();                // 获取响应

                con2 = Jsoup.connect("http://www.biquge.com.tw" + newNovels.get( names.get(j) ));
                // 设置cookie和post上面的map数据
                index = con2.ignoreContentType(true).followRedirects(true).method(Connection.Method.GET)
                        .cookies(rs.cookies()).execute();

                // 将bodyStream通过GBK解码
                bf = new BufferedReader(new InputStreamReader(index.bodyStream(), "GBK"));
                line = "";
                sb = new StringBuilder(); // 用来存储响应数据
                // 循环读取流,若不到结尾处
                while ((line = bf.readLine()) != null) {
                    sb.append(line).append(System.getProperty("line.separator"));
                }
                bf.close();    // 重要且易忽略步骤 (关闭流,切记!)

                document = Jsoup.parse(sb.toString());
                Element div = document.getElementById("content");

                String content = div.html();
                content = content.replaceAll("&nbsp;", " ");
                content = content.replaceAll("<br>", "");

                novelService.updateLatestChapterByName(novel.getName(), names.get(j));
                SentMail.sentMail(names.get(j), content);

            }

        }



    }

}

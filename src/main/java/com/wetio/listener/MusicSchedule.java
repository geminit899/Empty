package com.wetio.listener;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wetio.entity.Music;
import com.wetio.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
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
 * @create 2018-2-6
 */

public class MusicSchedule {

    private MusicService musicService;
    private List<Music> musics;

    public void musicSchedule(ServletContextEvent sce){

        musicService = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext())
                .getBean(MusicService.class);

        try {
            getMusic();
        }catch (IOException e){
            System.out.println("Error occured in getMusic().");
        }

        writeMusic();

    }

    public void getMusic() throws IOException {
        musics = new ArrayList<>();
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

        JSONArray weekData = JSONArray.parseArray(allData.getString("allData"));

        for (int i = 0; i < weekData.size(); i++) {
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
                music.setSinger(singerString);
            } else
                music.setSinger(((JSONObject) singerArray.get(0)).getString("name"));

            music.setTop( i+1 );
            music.setName(song.getString("name"));
            music.setUrl("http://music.163.com/#/song?id=" + song.getString("id"));
            JSONObject albumObject = JSONObject.parseObject(song.getString("al"));
            music.setAlbum(albumObject.getString("name"));

            musics.add(music);
        }
    }

    public void writeMusic(){

        List<Music> musicInMysql = musicService.getMusic();

        if( musicInMysql.size() == 0 ){
            for(int i=0; i<musics.size(); i++){
                musicService.insertMusic(musics.get(i));
            }
            return;
        }

        Map<String, Music> map = new HashMap<>(musicInMysql.size());
        for(Music music : musicInMysql)
            map.put(music.getName(), music);

        for(Music music : musics){
            if( map.get(music.getName()) == null ){
                musicService.insertMusic(music);
            }
        }
    }

}

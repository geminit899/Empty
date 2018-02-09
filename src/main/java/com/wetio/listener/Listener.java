package com.wetio.listener;

import com.wetio.service.MusicService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Geminit
 * @create 2018-2-6
 */

@WebListener()
public class Listener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed). 
         You can initialize servlet context related data here.
      */
        MusicSchedule musicSchedule = new MusicSchedule();
        long musicSchedulePeriod = 24 * 60 * 60 * 1000; // h * m * s * ms
        Timer musicTimer = new Timer();
        musicTimer.schedule(new TimerTask(){
            @Override
            public void run() {
                // todo auto-generated method stub
                //执行你的任务类
                //musicSchedule.musicSchedule(sce);
            }
        }, new Date(), musicSchedulePeriod);

        NoticeSchedule noticeSchedule = new NoticeSchedule();
        long noticeSchedulePeriod = 1 * 5 * 60 * 1000; // h * m * s * ms
        Timer noticeTimer = new Timer();
        noticeTimer.schedule(new TimerTask(){
            @Override
            public void run() {
                // todo auto-generated method stub
                //执行你的任务类
                //noticeSchedule.noticeSchedule(sce);
            }
        }, new Date(), noticeSchedulePeriod);

        NovelSchedule novelSchedule = new NovelSchedule();
        long novelSchedulePeriod = 1 * 60 * 60 * 1000; // h * m * s * ms
        Timer novelTimer = new Timer();
        novelTimer.schedule(new TimerTask(){
            @Override
            public void run() {
                // todo auto-generated method stub
                //执行你的任务类
                //novelSchedule.novelSchedule(sce);
            }
        }, new Date(), novelSchedulePeriod);
    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
    }










    // Public constructor is required by servlet spec
    public Listener() {
    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {
      /* Session is created. */
    }

    public void sessionDestroyed(HttpSessionEvent se) {
      /* Session is destroyed. */
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is added to a session.
      */
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attibute
         is replaced in a session.
      */
    }
}

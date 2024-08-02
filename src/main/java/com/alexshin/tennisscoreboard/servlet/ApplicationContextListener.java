package com.alexshin.tennisscoreboard.servlet;

import com.alexshin.tennisscoreboard.util.HibernateUtil;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;


@SuppressWarnings("ResultOfMethodCallIgnored")
@WebListener
public class ApplicationContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        HibernateUtil.getSessionFactory();
    }
}

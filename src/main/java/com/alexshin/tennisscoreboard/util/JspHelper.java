package com.alexshin.tennisscoreboard.util;


public class JspHelper {
    private final static String JSP_PATH_PREFIX = "/WEB-INF/views/%s.jsp";

    public static String getPath(String jspName){
        return String.format(JSP_PATH_PREFIX, jspName);
    }

}

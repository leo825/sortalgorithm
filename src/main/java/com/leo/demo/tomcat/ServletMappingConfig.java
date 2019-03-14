package com.leo.demo.tomcat;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @Date 2019/3/12 17:40
 * @TODO servlet配置模拟web.xml中的<servlet></serlvet>配置
 */
public class ServletMappingConfig {
    /**
     * 放置接口请求的路径
     */
    public static List<ServletMapping> servletMappingList = new ArrayList<ServletMapping>();
    static{
        servletMappingList.add(new ServletMapping("findGirl","/girl","com.leo.demo.tomcat.FindGirlServlet"));
        servletMappingList.add(new ServletMapping("helloWorld","/hello","com.leo.demo.tomcat.HelloWorldServlet"));
    }

}

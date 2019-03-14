package com.leo.demo.tomcat;

/**
 * @author Administrator
 * @Date 2019/3/12 17:37
 * @TODO servlet配置
 */
public class ServletMapping {
    /**
     * 接口名称
     */
    private String servletName;
    /**
     * 接口路径
     */
    private String url;
    /**
     * 接口包名
     */
    private String clazz;

    public ServletMapping(String servletName, String url, String clazz) {
        this.servletName = servletName;
        this.url = url;
        this.clazz = clazz;
    }

    public String getServletName() {
        return servletName;
    }

    public void setServletName(String servletName) {
        this.servletName = servletName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }
}

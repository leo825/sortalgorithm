package com.leo.demo.tomcat;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Administrator
 * @Date 2019/3/12
 * @TODO 封装请求对象           通过输入流，对HTTP协议进行解析，拿到了HTTP请求头的方法以及URL
 */
public class MyRequest {

    private String url;
    private String method;

    public MyRequest(InputStream is) throws IOException {
        String httpRequest = "";
        byte[] httpRequestBytes = new byte[1024];
        int length = 0;
        if ((length = is.read(httpRequestBytes)) > 0) {
            httpRequest = new String(httpRequestBytes, 0, length);
        }
        //HTTP请求协议
        //GET/favicon.ico HTTP/1.1
        //Accept:*/*
        //User-Agent:Mozilla/5.0
        //Host:localhsot:8080
        //Connection:Keep-Alive
        String httpHead = httpRequest.split("\n")[0];//换行
        url = httpHead.split("\\s")[1];//表示单词边界，要将字符串\b传给正则就得首先对\转义，用\\表示
        method = httpHead.split("\\s")[0];
        System.out.println(this);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "MyRequest{" +
                "url='" + url + '\'' +
                ", method='" + method + '\'' +
                '}';
    }
}

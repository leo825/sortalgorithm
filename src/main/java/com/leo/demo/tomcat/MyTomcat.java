package com.leo.demo.tomcat;

import com.sun.deploy.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @Date 2019/3/12 17:55
 * @TODO mytomcat核心启动类
 */
public class MyTomcat {
    private int port = 8080;
    private Map<String, String> urlServletMap = new HashMap<String, String>();

    public MyTomcat(int port) {
        this.port = port;
    }

    public void start() {
        //初始化 URL与对应的处理的servlet的关系
        initServletMapping();
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("MyTomcat is start...");
            while (true) {
                Socket socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();
                MyRequest myRequest = new MyRequest(inputStream);
                MyResponse myResponse = new MyResponse(outputStream);

                //请求分发
                dispatch(myRequest, myResponse);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 匹配映射地址和方法
     *
     * @param myRequest
     * @param myResponse
     */
    private void dispatch(MyRequest myRequest, MyResponse myResponse) {
        try {
            String clazz = urlServletMap.get(myRequest.getUrl());
            if (clazz == null || "".equals(clazz)) {
                System.out.println(myRequest.getUrl() + " 请求路径不存在");
                return;
            } else {
                System.out.println(clazz);
            }
            Class<MyServlet> myServletClass = (Class<MyServlet>) Class.forName(clazz);
            MyServlet myServlet = myServletClass.newInstance();
            myServlet.service(myRequest, myResponse);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化请求路径
     */
    private void initServletMapping() {
        for (ServletMapping servletMapping : ServletMappingConfig.servletMappingList) {
            urlServletMap.put(servletMapping.getUrl(), servletMapping.getClazz());
        }
    }

    /**
     * 启动main方法
     *
     * @param args
     */
    public static void main(String[] args) {
        new MyTomcat(8080).start();
    }
}

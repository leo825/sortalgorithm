package com.leo.demo.tomcat;

/**
 * @author Administrator
 * @Date 2019/3/12 17:16
 * @TODO 提供Servlet
 */
public abstract class MyServlet {
    /**
     * get请求
     * @param myRequest
     * @param myResponse
     */
    public abstract void doGet(MyRequest myRequest, MyResponse myResponse);

    /**
     * post请求
     * @param myRequest
     * @param myResponse
     */
    public abstract void doPost(MyRequest myRequest, MyResponse myResponse);

    public void service(MyRequest myRequest, MyResponse myResponse) {
        if (myRequest.getMethod().equalsIgnoreCase("POST")) {
            doPost(myRequest, myResponse);
        } else if (myRequest.getMethod().equalsIgnoreCase("GET")) {
            doGet(myRequest, myResponse);
        }
    }
}

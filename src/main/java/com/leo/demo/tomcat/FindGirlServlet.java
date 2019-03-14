package com.leo.demo.tomcat;

import java.io.IOException;

/**
 * Created by Administrator on 2019/3/12.
 */
public class FindGirlServlet extends MyServlet{
    @Override
    public void doGet(MyRequest myRequest, MyResponse myResponse) {
        try {
            myResponse.write("get girl...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(MyRequest myRequest, MyResponse myResponse) {
        try {
            myResponse.write("post girl...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

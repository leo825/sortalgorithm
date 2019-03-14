package com.leo.demo.tomcat;

import java.io.IOException;

/**
 * @author Administrator
 * @Date 2019/3/14 11:07
 * @TODO
 */
public class ExecptionServlet extends MyServlet {
    @Override
    public void doGet(MyRequest myRequest, MyResponse myResponse) {
        doPost(myRequest,myResponse);
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

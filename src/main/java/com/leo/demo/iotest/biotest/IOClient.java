package com.leo.demo.iotest.biotest;

/**
 * @ClassName: BIOClient
 * @Description: ${description}
 * @Author: leo825
 * @Date: 2019-12-19 14:16
 * @Version: 1.0
 */

import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 客户端实现的内部类
 */
public class IOClient {


    /**
     * 格式化时间并且获取当前时间
     */
    public static String getNowDateTime() {
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(date);
    }

    public static void main(String[] args) {
        for (int i = 3; i <= 5; i++) {
            final int finalI = i;
            new Thread(() -> {
                try {
                    Socket socket = new Socket("127.0.0.1", 8000);
                    String socketName = "Socket-" + socket.getLocalPort();
                    System.out.println(socketName + "客户端已经启动了");
                    while (true) {
                        try {
                            String content = "[" + socketName + "]" + getNowDateTime() + ": hello world";
                            System.out.println("发送内容：" + content);
                            socket.getOutputStream().write(content.getBytes());
                            socket.getOutputStream().flush();
                            TimeUnit.SECONDS.sleep(finalI);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }
}

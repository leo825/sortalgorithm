package com.leo.demo.iotest.biotest;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Administrator
 * @Date 2019/3/15 16:25
 * @TODO BIO的实现
 * <p>
 * 服务器端启动 ServerSocket，端口 0 表示自动绑定一个空闲端口。
 * <p>
 * 调用 accept 方法，阻塞等待客户端连接。
 * <p>
 * 利用 Socket 模拟了一个简单的客户端，只进行连接、读取、打印。
 * <p>
 * 当连接建立后，启动一个单独线程负责回复客户端请求。
 * <p>
 * 这样，一个简单的 Socket 服务器就被实现出来了。
 */
public class BIOServer {

    public static void main(String[] args) throws Exception {

        ServerSocket serverSocket = new ServerSocket(8000);

        // (1) 接收新连接线程
        new Thread(() -> {
            while (true) {
                try {
                    // (1) 阻塞方法获取新的连接
                    Socket socket = serverSocket.accept();

                    // (2) 每一个新的连接都创建一个线程，负责读取数据
                    new Thread(() -> {
                        String serverSocketName = "服务端启动的线程：Server-" + socket.getPort();
                        System.out.println(serverSocketName + "-" + Thread.currentThread().getName());
                        try {
                            byte[] data = new byte[1024];
                            InputStream inputStream = socket.getInputStream();
                            while (true) {
                                int len;
                                // (3) 按字节流方式读取数据
                                while ((len = inputStream.read(data)) != -1) {
                                    System.out.println(new String(data, 0, len));
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }).start();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();

        System.out.println("BIOServer已经启动了");
    }
}


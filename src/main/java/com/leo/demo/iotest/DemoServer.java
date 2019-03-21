package com.leo.demo.iotest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Administrator
 * @Date 2019/3/15 16:25
 * @TODO BIO的实现
 * <p/>
 * 服务器端启动 ServerSocket，端口 0 表示自动绑定一个空闲端口。
 * <p/>
 * 调用 accept 方法，阻塞等待客户端连接。
 * <p/>
 * 利用 Socket 模拟了一个简单的客户端，只进行连接、读取、打印。
 * <p/>
 * 当连接建立后，启动一个单独线程负责回复客户端请求。
 * <p/>
 * 这样，一个简单的 Socket 服务器就被实现出来了。
 */
public class DemoServer extends Thread {
    private ServerSocket serverSocket;

    public int getPort() {
        return serverSocket.getLocalPort();
    }

    public void run() {
        try {
            serverSocket = new ServerSocket(0);
            while (true) {
                Socket socket = serverSocket.accept();
                RequestHandler requestHandler = new RequestHandler(socket);
                requestHandler.start();
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


    //客户端内部类
    private class RequestHandler extends Thread {
        private Socket socket;

        RequestHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            /**
             * 但是在 try-with-resources 结构中，异常处理也有两种情况（注意，不论 try 中是否有异常，都会首先自动执行 close 方法，然后才判断是否进入 catch 块
             * try 块没有发生异常时，自动调用 close 方法，如果发生异常，catch 块捕捉并处理异常。
             * try 块发生异常，然后自动调用 close 方法，如果 close 也发生异常，catch 块只会捕捉 try 块抛出的异常，close 方法的异常会在catch 中被压制，
             * 但是你可以在catch块中，用 Throwable.getSuppressed 方法来获取到压制异常的数组。
             */
            try (PrintWriter out = new PrintWriter(socket.getOutputStream());) {
                while (true) {
                    out.println("Hello world!");
                    out.flush();
                    Thread.sleep(3000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 简化实现，不做读取，直接发送字符串
    public static void main(String[] args) throws IOException {
        DemoServer server = new DemoServer();
        server.start();
        try (Socket client = new Socket(InetAddress.getLocalHost(), server.getPort())) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));

            /**
             *  while ((bufferedReader.read()) != -1) {
             *    System.out.println(bufferedReader.readLine());
             * }
             * 发现每行的第一个字符都没有显示出来，原因呢：b=bf.read())!=-1  每次都会先读取一个字节出来，所以后面的bf.readLine());
             * 读取的就是每行少一个字节
             */
            String line = null;
            while ((line = (bufferedReader.readLine())) != null) {
                System.out.println(line);
            }
        }
    }


}


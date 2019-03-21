package com.leo.demo.iotest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Administrator
 * @Date 2019/3/16 9:44
 * @TODO NIO实现的实例
 * 首先，通过 Selector.open() 创建一个 Selector，作为类似调度员的角色。
 * <p/>
 * 然后，创建一个 ServerSocketChannel，并且向 Selector 注册，通过指定 SelectionKey.OP_ACCEPT，告诉调度员，它关注的是新的连接请求。
 * 注意：为什么我们要明确配置非阻塞模式呢？这是因为阻塞模式下，注册操作是不允许的，会抛出 IllegalBlockingModeException 异常。
 * <p/>
 * Selector 阻塞在 select 操作，当有 Channel 发生接入请求，就会被唤醒。
 * <p/>
 * 在 sayHelloWorld 方法中，通过 SocketChannel 和 Buffer 进行数据操作，在本例中是发送了一段字符串。
 * <p/>
 * 可以看到，在前面两个样例中，IO 都是同步阻塞模式，所以需要多线程以实现多任务处理。
 * 而 NIO 则是利用了单线程轮询事件的机制，通过高效地定位就绪的 Channel，来决定做什么，仅仅 select 阶段是阻塞的，可以有效避免大量客户端连接时，
 * 频繁线程切换带来的问题，应用的扩展能力有了非常大的提高。下面这张图对这种实现思路进行了形象地说明。
 */
public class NIOServer extends Thread {
    public void run() {
        try (Selector selector = Selector.open(); ServerSocketChannel serverSocket = ServerSocketChannel.open();) {// 创建 Selector 和 Channel
            serverSocket.bind(new InetSocketAddress(InetAddress.getLocalHost(), 8888));
            serverSocket.configureBlocking(false); // 注册到 Selector，并说明关注点
            serverSocket.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                selector.select();// 阻塞等待就绪的 Channel，这是关键点之一
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> iter = selectedKeys.iterator();
                while (iter.hasNext()) {
                    SelectionKey key = iter.next(); // 生产系统中一般会额外进行就绪状态检查
                    sayHelloWorld((ServerSocketChannel) key.channel());
                    iter.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sayHelloWorld(ServerSocketChannel server) throws IOException {
        try (SocketChannel client = server.accept();) {
            client.write(Charset.defaultCharset().encode("Hello world!"));
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
package com.leo.demo.loadbalance.mytest;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName: MyLoadBalanceTest
 * @Description: 关于负载均衡的测试
 * 目标：
 * 1、使用轮询的负载均衡算法
 * 2、如果是一个节点挂了，请求转发到其他节点
 * @Author: leo825
 * @Date: 2019-08-20 17:54
 * @Version: 1.0
 */
public class MyLoadBalanceTest {

    /**
     * 定义一个全局的ipMap
     * key值为IP地址
     * value值为权重值，当权重值为0的时候认为此ip地址失效
     */
    private final static ConcurrentHashMap<String, Integer> ipMap = new ConcurrentHashMap();

    static {
        ipMap.put("192.168.1.1", 1);
        ipMap.put("192.168.1.2", 1);
        ipMap.put("192.168.1.3", 1);
        ipMap.put("192.168.1.4", 1);
        ipMap.put("192.168.1.5", 1);
    }

    //使用原子变量，以防并发修改
    private final static AtomicInteger tempCount = new AtomicInteger(0);

    //使用线程安全的list以防止并发访问
    private final static Vector<String> ipList = new Vector();

    //调用地址的超时时间设置
    private final static int COST_TIMEOUT = 10;


    /**
     * 轮训方法要考虑到并发访问情况
     *
     * @return
     */
    public static String getRoundRobinIp() {
        //每次需要清空原有的list
        ipList.removeAllElements();
        //重新把ip地址放进list中
        for (ConcurrentMap.Entry<String, Integer> entry : ipMap.entrySet()) {
            String availabelIpAddr = entry.getKey();
            int weigtVal = entry.getValue();
            for (int i = 0; i < weigtVal; i++) {
                ipList.add(availabelIpAddr);
            }
        }

        if (tempCount.get() >= ipList.size()) {
            tempCount.set(0);
        }

        String ipAddr = "";
        if (!ipList.isEmpty()) {
            ipAddr = ipList.get(tempCount.get());
            tempCount.incrementAndGet();
        }
        return ipAddr;
    }

    /**
     * 模拟业务访问的时间
     */
    public static String getBusiIpAddress(String threadName) throws InterruptedException {
        //随机模拟业务运行时间
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        //生成一个业务访问的时间
        int costTime = threadLocalRandom.nextInt(5, 15);
        String ipAddress = getRoundRobinIp();
        System.out.println(threadName + "请求的地址为：" + ipAddress);
        System.out.println(threadName + "运行时间为：" + costTime + "s");
        Thread.sleep(costTime * 1000);
        //业务执行超时了，需要重新执行获取地址执行业务
        if (costTime >= COST_TIMEOUT) {
            System.out.println(threadName + "请求" + ipAddress + "接口地址超时了，请切换请求地址");
            ipMap.put(ipAddress, 0);
            System.out.println(threadName + "当前ip地址列表：" + ipMap.toString());
            ipAddress = getRoundRobinIp();
            System.out.println(threadName + "重新获取的请求地址为：" + ipAddress);
            //如果所有地址都已经不通了，直接返回""
            if ("".equals(ipAddress)) {
                System.out.println("所有的节点都不通了，此处抛出异常");
                return "";
            } else {
                //使用递归调用获取可用的ip地址，直到获取可以使用的地址
                return getBusiIpAddress(threadName);
            }
        } else {
            return ipAddress;
        }
    }

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        /**
         * 设置10个线程
         */
        int childProcess = 10;

        /**
         * 设置10+1个屏障，因为主线程也需要阻塞，主线程要先到终点
         */
        CyclicBarrier cyclicBarrier = new CyclicBarrier(childProcess + 1);
        /**
         * 设置主线程准备时间
         */
        CyclicBarrier cyclicBarrierReady = new CyclicBarrier(childProcess + 1);

        //比赛准备时间
        long beginTime = System.currentTimeMillis();

        System.out.println(Thread.currentThread().getName() + "主线程开始运行...");
        //主线程准备时间
        long endTime = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + "准备时间：" + (endTime - beginTime) + "ms");

        //运动员进入各自的跑道
        for (int i = 0; i < childProcess; i++) {
            Thread t = new Thread() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "线程准备就绪！！！");
                    try {
                        //线程阻塞，等待主线程完成的信号
                        cyclicBarrierReady.await();
                        String threadName = Thread.currentThread().getName();
                        //模拟线程执行业务所消耗的时间
                        String ipAddress = getBusiIpAddress(threadName);
                        System.out.println(threadName + "最终获取的地址为：" + ipAddress);
                        //线程排队，当到达屏障值就释放锁
                        cyclicBarrier.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            t.start();
        }
        //主线程通知开始运行
        cyclicBarrierReady.await();
        //等待各个子线程完成运行,主线程就是第10+1个线程
        cyclicBarrier.await();
        System.out.println(Thread.currentThread().getName() + "所有线程执行结束了");
    }

}

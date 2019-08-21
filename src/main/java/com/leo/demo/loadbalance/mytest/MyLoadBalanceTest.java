package com.leo.demo.loadbalance.mytest;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
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
     */
    static TreeMap<String, Integer> ipMap = new TreeMap();
    static {
        ipMap.put("192.168.1.1",1);
        ipMap.put("192.168.1.2",1);
        ipMap.put("192.168.1.3",1);
        ipMap.put("192.168.1.4",1);
        ipMap.put("192.168.1.5",1);
    }

    //使用原子变量，以防并发修改
    static AtomicInteger tempCount = new AtomicInteger(0);
    public static String getRoundRobinIp(){

        Map<String,Integer> ipServerMap=new ConcurrentHashMap<>();
        ipServerMap.putAll(ipMap);

        Set<String> ipSet = ipServerMap.keySet();
        List<String> ipList = new ArrayList<>();
        ipList.addAll(ipSet);

        if(tempCount.get() >= ipList.size()){
            tempCount.set(0);
        }

        String ipAddr = ipList.get(tempCount.get());
        tempCount.incrementAndGet();
        return ipAddr;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++){
            System.out.println(getRoundRobinIp());
            if(i == 9){
                ipMap.remove("192.168.1.4");
                System.out.println("删除了key");
            }
        }

    }
}

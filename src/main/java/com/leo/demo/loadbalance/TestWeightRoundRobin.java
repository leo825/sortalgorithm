package com.leo.demo.loadbalance;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Administrator
 * @Date 2019/8/20 15:00
 * 加权轮询法：
 * 该算法中，每个机器接受的连接数量是按权重比例分配的。这是对普通轮询算法的改进，比如你可以设定：
 * 第三台机器的处理能力是第一台机器的两倍，那么负载均衡器会把两倍的连接数量分配给第3台机器。加权轮询分为：简单的轮询、平滑的轮询。
 * 什么是平滑的轮询，就是把每个不同的服务，平均分布。在Nginx源码中，实现了一种叫做平滑的加权轮询（smooth weighted round-robin balancing）
 * 的算法，它生成的序列更加均匀。5个请求现在分散开来，不再是连续的。
 */
public class TestWeightRoundRobin {
    //1.map, key-ip,value-weight
    static Map<String,Integer> ipMap=new HashMap<>();
    static {
        ipMap.put("192.168.13.1",1);
        ipMap.put("192.168.13.2",2);
        ipMap.put("192.168.13.3",4);


    }
    Integer pos=0;
    public String weightRoundRobin(){
        Map<String,Integer> ipServerMap=new ConcurrentHashMap<>();
        ipServerMap.putAll(ipMap);

        Set<String> ipSet=ipServerMap.keySet();
        Iterator<String> ipIterator=ipSet.iterator();

        //定义一个list放所有server
        ArrayList<String> ipArrayList=new ArrayList<String>();

        //循环set，根据set中的可以去得知map中的value，给list中添加对应数字的server数量
        while (ipIterator.hasNext()){
            String serverName=ipIterator.next();
            Integer weight=ipServerMap.get(serverName);
            for (int i = 0;i < weight ;i++){
                ipArrayList.add(serverName);
            }
        }
        String serverName=null;
        if (pos>=ipArrayList.size()){
            pos=0;
        }
        serverName=ipArrayList.get(pos);
        //轮询+1
        pos ++;


        return  serverName;
    }

    public static void main(String[] args) {
        TestWeightRoundRobin testWeightRoundRobin=new TestWeightRoundRobin();
        for (int i =0;i<10;i++){
            String server=testWeightRoundRobin.weightRoundRobin();
            System.out.println(server);
        }


    }
}
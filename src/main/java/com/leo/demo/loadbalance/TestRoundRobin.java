package com.leo.demo.loadbalance;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Administrator
 * @Date 2019/8/20 14:34
 *
 * 轮询法：
 * 轮询算法按顺序把每个新的连接请求分配给下一个服务器，最终把所有请求平分给所有的服务器。
 * 优点：绝对公平
 * 缺点：无法根据服务器性能去分配，无法合理利用服务器资源。
 *
 * @TODO
 */
public class TestRoundRobin {


    //1.定义map, key-ip,value-weight
    static Map<String,Integer> ipMap=new HashMap<>();
    static {
        ipMap.put("192.168.13.1",1);
        ipMap.put("192.168.13.2",1);
        ipMap.put("192.168.13.3",1);

    }

    //Integer sum=0;
    Integer  pos = 0;

    public String RoundRobin(){
        Map<String,Integer> ipServerMap=new ConcurrentHashMap<>();
        ipServerMap.putAll(ipMap);

        //2.取出来key,放到set中
        Set<String> ipset=ipServerMap.keySet();

        //3.set放到list，要循环list取出
        ArrayList<String> iplist=new ArrayList<String>();
        iplist.addAll(ipset);
        String serverName=null;

        //4.定义一个循环的值，如果大于set就从0开始
        synchronized(pos){
            if (pos>=ipset.size()){
                pos=0;
            }
            serverName=iplist.get(pos);
            //轮询+1
            pos ++;
        }
        return serverName;

    }

    public static void main(String[] args) {
        TestRoundRobin testRoundRobin=new TestRoundRobin();
        for (int i=0;i<10;i++){
            String serverIp=testRoundRobin.RoundRobin();
            System.out.println(serverIp);
        }
    }
}
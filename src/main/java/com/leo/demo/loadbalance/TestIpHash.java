package com.leo.demo.loadbalance;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Administrator
 * @Date 2019/8/20 15:13
 * IP_Hash算法：
 * hash(object)%N算法，通过一种散列算法把请求分配到不同的服务器上。
 */
public class TestIpHash {
    //    1.定义map, key-ip,value-weight
    static Map<String, Integer> ipMap = new HashMap<>();

    static {
        ipMap.put("192.168.13.1", 1);
        ipMap.put("192.168.13.2", 2);
        ipMap.put("192.168.13.3", 4);
    }

    public String ipHash(String clientIP) {
        Map<String, Integer> ipServerMap = new ConcurrentHashMap<>();
        ipServerMap.putAll(ipMap);

        //2.取出来key,放到set中
        Set<String> ipset = ipServerMap.keySet();

        //3.set放到list，要循环list取出
        ArrayList<String> iplist = new ArrayList<String>();
        iplist.addAll(ipset);

        //对ip的hashcode值取余数，每次都一样的
        int hashCode = clientIP.hashCode();
        int serverListsize = iplist.size();
        int pos = hashCode % serverListsize;
        return iplist.get(pos);

    }

    public static void main(String[] args) {
        TestIpHash testIpHash = new TestIpHash();
        String servername = testIpHash.ipHash("192.168.21.2");
        System.out.println(servername);
    }

}
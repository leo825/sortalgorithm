package com.leo.demo.threadtest.locktest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Administrator
 * @Date 2019/7/10 13:16
 * @TODO
 */
public class TestThreadDemo2 implements Runnable {
    public static ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<String, String>();

    @Override
    public void run() {
        getThreadLog(getFileName());
    }

    public synchronized String getFileName() {
        try {
            String path = "FILENAME_" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            if (!concurrentHashMap.containsKey(path)) {
                if (existPath(path)) {
                    return path;
                } else {
                    return getFileName();
                }
            } else {
                getThreadLog("此文件路径已经存在了，请等待创建。。。");
                this.wait(2000);
                return getFileName();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 查看路径是否存在
     *
     * @return
     */
    public boolean existPath(String path) {
        synchronized (TestThreadDemo2.class) {
            if (!concurrentHashMap.containsKey(path)) {
                getThreadLog("不存在此路径，正在创建此路径");
                concurrentHashMap.put(path, path);
                return concurrentHashMap.containsKey(path);
            } else {
                getThreadLog("此路径已经存在了，需要等待创建");
                return false;
            }
        }
    }

    /**
     * 获取线程名和时间
     *
     * @return
     */
    public void getThreadLog(String logContent) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[");
        stringBuffer.append(Thread.currentThread().getName());
        stringBuffer.append(" ");
        stringBuffer.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()));
        stringBuffer.append("]");
        stringBuffer.append(logContent);
        System.out.println(stringBuffer.toString());
    }
}





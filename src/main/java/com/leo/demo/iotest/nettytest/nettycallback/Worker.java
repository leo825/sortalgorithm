package com.leo.demo.iotest.nettytest.nettycallback;

/**
 * @ClassName: Worker
 * @Description: 异步工作线程
 * @Author: leo825
 * @Date: 2020-01-16 16:02
 * @Version: 1.0
 */
public interface Worker {
    String action(Object object);
}

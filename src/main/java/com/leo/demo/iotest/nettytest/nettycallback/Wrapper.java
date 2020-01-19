package com.leo.demo.iotest.nettytest.nettycallback;

/**
 * @ClassName: Wrapper
 * @Description: 定义一个装饰器来讲worker和回调器包装一下
 * @Author: leo825
 * @Date: 2020-01-16 16:06
 * @Version: 1.0
 */
public class Wrapper {
    private Object param;
    private Worker worker;
    private Listener listener;

    public Object getParam() {
        return param;
    }

    public void setParam(Object param) {
        this.param = param;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public Listener getListener() {
        return listener;
    }

    public void addListener(Listener listener) {
        this.listener = listener;
    }
}

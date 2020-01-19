package com.leo.demo.iotest.nettytest.nettycallback;

/**
 * @ClassName: BootStrapMain
 * @Description: 主逻辑
 * @Author: leo825
 * @Date: 2020-01-16 16:08
 * @Version: 1.0
 */
public class BootStrapMain {
    public static void main(String[] args) {
        BootStrapMain bootStrapMain = new BootStrapMain();
        //创建一个worker
        Worker worker = new Worker() {
            @Override
            public String action(Object object) {
                try {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + " 这是一个耗时程序");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return object + " world";
            }
        };

        Wrapper wrapper = new Wrapper();
        wrapper.setWorker(worker);
        wrapper.setParam("hello");
        bootStrapMain.doWork(wrapper).addListener(new Listener() {
            @Override
            public void result(Object result) {
                System.out.println(Thread.currentThread().getName() + " 回调结果：" + result);
            }
        });
        System.out.println(Thread.currentThread().getName());
    }


    /**
     * 包装器包装一下
     * @param wrapper
     * @return
     */
    private Wrapper doWork(Wrapper wrapper) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " 添加监听器");
                Worker worker = wrapper.getWorker();
                //执行action方法
                String result = worker.action(wrapper.getParam());
                //执行结果回调
                wrapper.getListener().result(result);
            }
        }).start();
        return wrapper;
    }


}

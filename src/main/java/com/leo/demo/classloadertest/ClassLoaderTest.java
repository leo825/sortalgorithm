package com.leo.demo.classloadertest;

import java.io.FileInputStream;
import java.lang.reflect.Method;

/**
 * @author Administrator
 * @Date 2019/5/8 14:11
 * @TODO
 */
public class ClassLoaderTest {

    public void hello() {
        System.out.println("嗯，是的，我是由" + getClass().getClassLoader().getClass() + "加载进来的");
    }

    static class MyClassLoader extends ClassLoader {
        //自定义的class文件路径
        private String classPath;

        public MyClassLoader(String classPath) {
            this.classPath = classPath;
        }

        private byte[] loadByte(String name) throws Exception {
            name = name.replaceAll("\\.", "/");
            FileInputStream fis = new FileInputStream(classPath + "/" + name + ".class");
            int len = fis.available();
            byte[] data = new byte[len];
            fis.read(data);
            fis.close();
            return data;
        }

        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                byte[] data = loadByte(name);
                return defineClass(name, data, 0, data.length);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ClassNotFoundException();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        MyClassLoader classLoader = new MyClassLoader("E:\\WorkSpace\\CLASS_TEST");
        Class clazz = classLoader.loadClass("com.leo.demo.classloadertest.MyTest");
        Object obj = clazz.newInstance();
        Method helloMethod = clazz.getDeclaredMethod("hello", null);
        helloMethod.invoke(obj, null);
    }

}

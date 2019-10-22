package com.leo.demo.classloadertest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
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

    static class MyClassLoader1 extends ClassLoader {
        //自定义的class文件路径
        private String classPath;

        public MyClassLoader1(String classPath) {
            this.classPath = classPath;
        }

        private byte[] loadByte(String name) throws Exception {
            System.out.println("加载地址：" + classPath + "/" + name.replace(".", "/") + ".class");
            FileInputStream fis = new FileInputStream(classPath + "/" + name.replace(".", "/") + ".class");
            int len = fis.available();
            byte[] data = new byte[len];
            fis.read(data);
            fis.close();
            return data;
        }

        /**
         * 重写findClass方法
         * 重写findClass方法的自定义类，首先会通过父类加载器进行加载，如果所有父类加载器都无法加载，再通过用户自定义的findClass方法进行加载。
         * 如果父类加载器可以加载这个类或者当前类已经存在于某个父类的容器中了，这个类是不会再次被加载的，此时用户自定义的findClass方法就不会被执行了。
         *  重写findClass方法是符合双亲委派模式的，它保证了相同全限定名的类是不会被重复加载到JVM中
         *
         * @param name
         * @return
         * @throws ClassNotFoundException
         */

        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                byte[] data = loadByte(name);
                return defineClass(name, data, 0, data.length);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ClassNotFoundException();
            }
        }

        /**
         * 重写loadClass方法会破坏双亲委派机制
         * 如果要想在JVM的不同类加载器中保留具有相同全限定名的类，那就要通过重写loadClass来实现，此时首先是通过用户自定义的类加载器来判断该类是否可加载，
         * 如果可以加载就由自定义的类加载器进行加载，如果不能够加载才交给父类加载器去加载
         *
         * @param name
         * @return
         * @throws ClassNotFoundException
         */
//        @Override
//        public Class<?> loadClass(String name) throws ClassNotFoundException {
//            try {
//                // 这个getClassInputStream根据情况实现
//                byte[] bt = loadByte(name);
//                return defineClass(name, bt, 0, bt.length);
//            } catch (Exception e) {
//                throw new ClassNotFoundException("Class " + name + " not found.");
//            }
//        }

    }

    static class MyClassLoader2 extends ClassLoader {
        //自定义的class文件路径
        private String classPath;

        public MyClassLoader2(String classPath) {
            this.classPath = classPath;
        }

        private byte[] loadByte(String name) throws Exception {
            System.out.println("加载地址：" + classPath + "/" + name.replace(".", "/") + ".class");
            FileInputStream fis = new FileInputStream(classPath + "/" + name.replace(".", "/") + ".class");
            int len = fis.available();
            byte[] data = new byte[len];
            fis.read(data);
            fis.close();
            return data;
        }

        /**
         * 重写findClass方法
         * 重写findClass方法的自定义类，首先会通过父类加载器进行加载，如果所有父类加载器都无法加载，再通过用户自定义的findClass方法进行加载。
         * 如果父类加载器可以加载这个类或者当前类已经存在于某个父类的容器中了，这个类是不会再次被加载的，此时用户自定义的findClass方法就不会被执行了。
         *  重写findClass方法是符合双亲委派模式的，它保证了相同全限定名的类是不会被重复加载到JVM中
         *
         * @param name
         * @return
         * @throws ClassNotFoundException
         */
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                byte[] data = loadByte(name);
                return defineClass(name, data, 0, data.length);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ClassNotFoundException();
            }
        }

        /**
         * 重写loadClass方法会破坏双亲委派机制
         * 如果要想在JVM的不同类加载器中保留具有相同全限定名的类，那就要通过重写loadClass来实现，此时首先是通过用户自定义的类加载器来判断该类是否可加载，
         * 如果可以加载就由自定义的类加载器进行加载，如果不能够加载才交给父类加载器去加载
         *
         * @param name
         * @return
         * @throws ClassNotFoundException
         */
        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {
            try {
                // 这个getClassInputStream根据情况实现
                String myTestClassPath = classPath + "/" + name.replace(".", "/") + ".class";
                System.out.println("加载地址：" + myTestClassPath);
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(myTestClassPath);
                } catch (FileNotFoundException e) {
                    System.out.println("文件不存在");
                }
                if (fis == null) {
                    System.out.println("父类加载地址：" + name);
                    return super.loadClass(name);
                }
                byte[] bt = new byte[fis.available()];
                fis.read(bt);
                return defineClass(name, bt, 0, bt.length);
            } catch (Exception e) {
                    throw new ClassNotFoundException("Class " + name + " not found.");
            }
        }

    }


    /**
     * 测试findClass方法，使用findClass方法会执行构造方法
     */
    public static void findClassTest() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        MyClassLoader1 classLoader = new MyClassLoader1("E:/WorkSpace/CLASS_TEST");
        System.out.println(classLoader.getClass());
        Class clazz = classLoader.findClass("com.leo.demo.classloadertest.MyTest");
        Method helloMethod = clazz.getDeclaredMethod("hello", null);
        helloMethod.invoke(null, null);
    }

    /**
     * 测试loadClass方法
     */
    public static void loadClassTest1() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        MyClassLoader2 classLoader = new MyClassLoader2("E:/WorkSpace/CLASS_TEST");
        System.out.println(classLoader.getClass());

        Class clazz = classLoader.loadClass("com.leo.demo.classloadertest.MyTest");
        Object obj = clazz.newInstance();
        Method helloMethod = clazz.getDeclaredMethod("hello", null);
        helloMethod.invoke(obj, null);
    }

    /**
     * loadClass方法如果不调用newInstance的话是不会调用构造方法和static静态块的
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    public static void loadClassTest2() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        MyClassLoader2 classLoader = new MyClassLoader2("E:/WorkSpace/CLASS_TEST");
        System.out.println(classLoader.getClass());

        Class clazz = classLoader.loadClass("com.leo.demo.classloadertest.MyTest");
        Method helloMethod = clazz.getDeclaredMethod("hello", null);
        helloMethod.invoke(null, null);
    }

    /**
     * -XX:+TraceClassLoading 可以打印加载的类
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //findClassTest();
        //loadClassTest1();
        loadClassTest2();
    }

}

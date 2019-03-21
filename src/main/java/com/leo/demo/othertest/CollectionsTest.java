package com.leo.demo.othertest;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Administrator
 * @Date 2019/3/21 14:30
 * @TODO 关于集合的测试类
 * <p/>
 * 常用的集合类有哪些？比如List如何排序？
 * <p/>
 * 分两种，一种实现Set接口，一种是实现List接口的。
 * <p/>
 * Set：TreeSet,HashSet.
 * <p/>
 * List:ArrayList,LinkedList,Vector(线程安全)。
 * <p/>
 * JDK7以前用collections.sort(list,Comparator).
 * <p/>
 * JDK8直接用List.sort(Comparator).
 * <p/>
 * <p/>
 * 线程安全的集合对象：
 * Vector
 * HashTable
 * StringBuffer
 */
public class CollectionsTest {

    /**
     * List集合，其主要实现有LinkedList、ArrayList，前者实现了链表结构，后者可代表大小可变的数组。
     * 1、List的特点是能够以线性方式储存对象，并且允许放置重复对象。
     * 2、List能够利用Collections类的静态方法sort排序。
     * 3、sort(List list)自然排序；Sort(List listm,Comparator comparator)
     */
    private static class ListTest {

        /**
         * List:线性集合接口，有序；
         * ArrayList: 动态数组[可变长度的动态数组]
         */
        public static void arrayListTest() {
            List list = new ArrayList<>();
            list.add("1");
            list.add("2");
            list.add("3");

            System.out.println(list);
        }

        /**
         * LinkedList：链表结构的集合
         */
        public static void linkedListTest() {
            LinkedList list = new LinkedList<>();
            list.add("11");
            list.add("22");
            list.add("33");

            System.out.println(list);
            System.out.println(list.getFirst());
            System.out.println(list.getLast());
        }

        /**
         * vector:线程安全,实现和ArrayList很相像
         */
        public static void vectorTest() {
            Vector list = new Vector<>();
            list.add("111");
            list.add("222");
            list.add("333");
            System.out.println(list);
        }
    }

    /**
     * Set集合.其主要实现类有HashSet、TreeSet。存放对象的引用，不允许有重复对象。
     */
    private static class SetTest {

        /**
         * HashSet的实现
         */
        public static void hashSetTest() {
            Set set = new HashSet<>();
            set.add("abc");
            set.add("cba");
            set.add("abbc");
            set.add("bbc");
            System.out.println(set);
        }

        /**
         * treeSet的实现
         */
        public static void treeSetTest() {
            Set set = new TreeSet<>();
            set.add("abc");
            set.add("cba");
            set.add("abbc");
            set.add("bbc");
            System.out.println(set);
        }
    }

    /**
     * Map集合
     */
    private static class MapTest{

        /**
         * HashMap测试
         */
        public static void hashMapTest(){
            Map map = new HashMap<>();
            map.put("1","a");
            map.put("2","b");
            map.put("3","c");
            System.out.println(map);
        }

        /**
         * HashTable测试
         */
        public static void hashTableTest(){
            Map map = new Hashtable<>();
            map.put("1","aa");
            map.put("2","bb");
            map.put("3","cc");
            System.out.println(map);
        }

        /**
         * CurrentHashMap测试
         */
        public static void currentHashMapTest(){
            Map map = new ConcurrentHashMap<>();
            map.put("1","aaa");
            map.put("2","bbb");
            map.put("3","ccc");
            System.out.println(map);
        }
    }

    public static void main(String[] args) {
        ListTest.arrayListTest();
        ListTest.linkedListTest();
        ListTest.vectorTest();
        SetTest.hashSetTest();
        SetTest.treeSetTest();
        MapTest.hashMapTest();
        MapTest.hashTableTest();
        MapTest.currentHashMapTest();
    }

}
package com.leo.demo.othertest;

import java.util.*;

/**
 * @author Administrator
 * @Date 2019/3/18 13:41
 * @TODO
 */
public class LinkedListTest {


    /**
     * 倒序一个linkedList
     *
     * @param oldlinkedList
     * @return
     */
    public static LinkedList reverseLinkedList1(LinkedList<String> oldlinkedList) {
        LinkedList<String> newLinkList = new LinkedList<String>();
        for (int i = 0, length = oldlinkedList.size(); i < length; i++) {
            newLinkList.push(oldlinkedList.get(i));
        }
        return newLinkList;
    }

    /**
     * 倒序一个linkedList
     *
     * @param oldlinkedList
     * @return
     */
    public static LinkedList reverseLinkedList2(LinkedList<String> oldlinkedList) {
        Collections.reverse(oldlinkedList);
        return oldlinkedList;
    }

    /**
     * 倒序一个linkedList(jdk中的REVERSE_THRESHOLD < 18的时候使用的方法)
     *
     * @param oldlinkedList
     * @return
     */
    public static LinkedList reverseLinkedList3(LinkedList<String> oldlinkedList) {

        if (oldlinkedList == null || oldlinkedList.isEmpty()) {
            return null;
        }

        int length = oldlinkedList.size();
        String temp;

        /**
         * 第一个和倒数第一个交换位置
         * length >> 1 位运算，除以2
         */
        for (int i = 0, len = length >> 1, j = (length - 1) - i; i < len; i++, j--) {
            oldlinkedList.set(i, oldlinkedList.set(j, oldlinkedList.get(i)));
        }
        return oldlinkedList;
    }

    /**
     * 倒序一个linkedList(jdk中的REVERSE_THRESHOLD >= 18的时候使用的方法)
     *
     * @param oldlinkedList
     * @return
     */
    public static LinkedList reverseLinkedList4(LinkedList<String> oldlinkedList) {
        int size = oldlinkedList.size();
        ListIterator fwd = oldlinkedList.listIterator();
        ListIterator rev = oldlinkedList.listIterator(size);
        for (int i = 0, mid = oldlinkedList.size() >> 1; i < mid; i++) {
            Object tmp = fwd.next();
            fwd.set(rev.previous());
            rev.set(tmp);
        }
        return oldlinkedList;
    }

    /**
     * 倒序一个linkedList
     *
     * @param oldlinkedList
     * @return
     */
    public static LinkedList reverseLinkedList5(LinkedList<String> oldlinkedList) {
        if (oldlinkedList == null || oldlinkedList.isEmpty()) {
            return null;
        }
        LinkedList<String> newLinkList = new LinkedList<String>();
        Iterator<String> iterator = oldlinkedList.iterator();
        while (iterator.hasNext()) {
            String value = iterator.next();
            newLinkList.push(value);
        }
        return newLinkList;
    }

    /**
     * 比较转换时间的测试类
     */
    public static void costTimeTest() {
        int count = 10000000;
        LinkedList<String> linkedList = new LinkedList<String>();
        for (int i = 0; i < 10; i++) {
            linkedList.add(String.valueOf(i));
        }

        long stat = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            reverseLinkedList1(linkedList);
        }
        long end1 = System.currentTimeMillis();
        System.out.println("reverseLinkedList1耗时：" + (end1 - stat) + "ms");

        for (int i = 0; i < count; i++) {
            reverseLinkedList2(linkedList);
        }
        long end2 = System.currentTimeMillis();
        System.out.println("reverseLinkedList2耗时：" + (end2 - end1) + "ms");

        for (int i = 0; i < count; i++) {
            reverseLinkedList3(linkedList);
        }
        long end3 = System.currentTimeMillis();
        System.out.println("reverseLinkedList3耗时：" + (end3 - end2) + "ms");

        for (int i = 0; i < count; i++) {
            reverseLinkedList4(linkedList);
        }
        long end4 = System.currentTimeMillis();
        System.out.println("reverseLinkedList4耗时：" + (end4 - end3) + "ms");

        for (int i = 0; i < count; i++) {
            reverseLinkedList5(linkedList);
        }
        long end5 = System.currentTimeMillis();
        System.out.println("reverseLinkedList5耗时：" + (end5 - end4) + "ms");
    }

    /**
     * ListIterator 测试方法
     */
    public static void ListIteratorTest() {
        LinkedList<String> linkedList = new LinkedList<String>();
        for (int i = 0; i < 18; i++) {
            linkedList.add(String.valueOf(i));
        }
        int size = linkedList.size();
        ListIterator<String> iterator1 = linkedList.listIterator();
        //将游标设置到链表的最后一个地方
        ListIterator<String> iterator2 = linkedList.listIterator(size);

        while (iterator2.hasPrevious()) {
            System.out.print(iterator2.previous() + ",");
        }
    }

    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<String>();
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
        linkedList.add("4");
        linkedList.add("5");
        linkedList.add("6");
        linkedList.add("7");
        linkedList.add("8");

        System.out.println(linkedList);

        System.out.println(reverseLinkedList3(linkedList));

        costTimeTest();
        ListIteratorTest();
    }
}



package com.leo.demo.othertest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2019/2/25.
 */
public class ArrayTest {

    /**
     * 将数组元素倒置
     */
    public static String[] reverseArray(String[] strArray) {
        List<String> list = new ArrayList<String>();
        Collections.addAll(list, strArray);
        Collections.reverse(list);
        strArray = (String[]) list.toArray(new String[list.size()]);//(String[])list.toArray()会抛异常

        return strArray;
    }

    public static void main(String[] agrs) {
        String[] a = {"1", "2", "3", "4"};
        a = reverseArray(a);
        for (String i : a) {
            System.out.print(i + " ");
        }
    }
}

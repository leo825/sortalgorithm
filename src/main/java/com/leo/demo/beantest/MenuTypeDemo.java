package com.leo.demo.beantest;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: MenuTypeDemo
 * @Description: 需求：比较N个相同bean的某几个属性是否一致，将相同bean的枚举出来，有通用方法或者思路吗？
 * @Author: leo825
 * @Date: 2019-10-17 15:59
 * @Version: 1.0
 */
public class MenuTypeDemo {
    public static void main(String[] args) {
        /*****************第一种排列*************************/
        String[] color1 = {"红色", "白色"};
        String[] size1 = {"4.7寸"};
        String[] test1 = {"默认"};
        String[] version1 = {"联通", "电信", "移动"};
        String[] type1 = {"小米"};
        List<String[]> dataList1 = new ArrayList<String[]>();
        dataList1.add(color1);
        dataList1.add(size1);
        dataList1.add(test1);
        dataList1.add(version1);
        dataList1.add(type1);
        /*********************第二种排列 多出一个非默认类型，其中默认类型和第一种排列应该是全部相同的****************************/
        String[] color2 = {"红色", "白色"};
        String[] size2 = {"4.7寸"};
        String[] test2 = {"默认", "非默认"};
        String[] version2 = {"联通", "电信"};
        String[] type2 = {"小米"};
        List<String[]> dataList2 = new ArrayList<String[]>();
        dataList2.add(color2);
        dataList2.add(size2);
        dataList2.add(test2);
        dataList2.add(version2);
        dataList2.add(type2);

        List<String[]> resultList1 = combination(dataList1, 0, null);
        List<String[]> resultList2 = combination(dataList2, 0, null);
        Object[] objList = {resultList1, resultList2};
        List<Phone> listPhone = new ArrayList<>();
        //打印组合结果
        for (Object obj : objList) {
            List<String[]> list = (List<String[]>) obj;
            for (int i = 0; i < list.size(); i++) {
                String[] objArr = list.get(i);
                Phone phone = new Phone();
                phone.setColor(objArr[0]);
                phone.setSize(objArr[1]);
                phone.setFromMede(objArr[2]);
                phone.setTypeName(objArr[3]);
                phone.setVersion(objArr[4]);
                listPhone.add(phone);
                System.out.println(phone.toString());
            }
        }
        System.out.println("总共有" + listPhone.size() + "组合");

        for (int i = 0; i < listPhone.size(); i++) {
            for (int j = i + 1; j < listPhone.size(); j++) {
                if (listPhone.get(i).compareTo(listPhone.get(j)) == 0) {
                    System.out.println("索引位为[" + i + "]" + (listPhone.get(i).toString()) + "和" + "索引位为[" + j + "]" + (listPhone.get(j).toString()));
                }
            }
        }
    }


    static class Phone implements Comparable{
        String fromMede;
        String typeName;
        String color;
        String size;
        String version;

        public Phone() {

        }

        public String getFromMede() {
            return fromMede;
        }

        public void setFromMede(String fromMede) {
            this.fromMede = fromMede;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        @Override
        public String toString() {
            return "Phone{" +
                    "fromMede='" + fromMede + '\'' +
                    ", typeName='" + typeName + '\'' +
                    ", color='" + color + '\'' +
                    ", size='" + size + '\'' +
                    ", version='" + version + '\'' +
                    '}';
        }

        @Override
        public int compareTo(Object o) {
            Phone phone = (Phone)o;
            if(((this.fromMede==null && phone.fromMede==null) || (this.fromMede!=null && this.fromMede.equals(phone.fromMede)))&&
            (((this.typeName==null && phone.typeName==null) || (this.typeName!=null&&this.typeName.equals(phone.typeName))))){
                return 0;
            }
            return -1;
        }
    }

    public static List<String[]> combination(List<String[]> dataList, int index, List<String[]> resultList) {
        if (index == dataList.size()) {
            return resultList;
        }

        List<String[]> resultList0 = new ArrayList<String[]>();
        if (index == 0) {
            String[] objArr = dataList.get(0);
            for (String str : objArr) {
                resultList0.add(new String[]{str});
            }
        } else {
            String[] strArr = dataList.get(index);
            for (String[] strArr0 : resultList) {
                for (String str : strArr) {
                    //复制数组并扩充新元素
                    String[] strArrCopy = new String[strArr0.length + 1];
                    System.arraycopy(strArr0, 0, strArrCopy, 0, strArr0.length);
                    strArrCopy[strArrCopy.length - 1] = str;
                    //追加到结果集
                    resultList0.add(strArrCopy);
                }
            }
        }
        return combination(dataList, ++index, resultList0);
    }
}
package com.leo.demo.beantest;

/**
 * @ClassName: BeanTest
 * @Description: 需求：比较N个相同bean的某几个属性是否一致，将相同bean的枚举出来，有通用方法或者思路吗？
 * @Author: leo825
 * @Date: 2019-10-17 15:35
 * @Version: 1.0
 */
public class BeanTest {

    //这几个属性相同的输出对象
    private String propertiesString = ",color,size,test,";

    public static void main(String[] args) {

        MyPhone myPhone1 = new MyPhone("红色", "4.7存", "默认", "联通", "小米");
        MyPhone myPhone2 = new MyPhone("红色", "4.7存", "默认", "电信", "小米");
        MyPhone myPhone3 = new MyPhone("红色", "4.7存", "默认", "移动", "小米");
        MyPhone myPhone4 = new MyPhone("红色", "4.7存", "默认", "联通", "小米");

       System.out.println(myPhone1.compareTo(myPhone3));
    }




    static class MyPhone implements Comparable{
        private String color;
        private String size;
        private String test;
        private String version;
        private String type;

        public MyPhone(String color, String size, String test, String version, String type) {
            this.color = color;
            this.size = size;
            this.test = test;
            this.version = version;
            this.type = type;
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

        public String getTest() {
            return test;
        }

        public void setTest(String test) {
            this.test = test;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @Override
        public int compareTo(Object o) {
            MyPhone myPhone = (MyPhone)o;
            if(((this.test==null && myPhone.getTest()==null) || (this.test!=null&&this.test.equals(myPhone.getTest())))&&
                    ((this.version==null && myPhone.getVersion()==null) || (this.version!=null&&this.version.equals(myPhone.getVersion())))){
                return 0;
            }
            return -1;
        }
    }
}

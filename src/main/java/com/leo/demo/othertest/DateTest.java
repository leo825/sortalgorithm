package com.leo.demo.othertest;

import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2019/2/25.
 * 切记，SimpleDateFormat是非线程安全的一定不要使用静态方法生成
 */
public class DateTest {

    /**
     * 格式化时间并且获取当前时间
     */
    public static String getNowDateTime() {
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(date);
    }

    /**
     * 根据时间戳获取当前时间
     */
    public static String getNowDateTimeFromTimesStamp() {
        long timeStamp = System.currentTimeMillis();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(new Date(Long.parseLong(String.valueOf(timeStamp))));//如果获取的是个字符串则:ruturn sf.format(new Date(Long.parseLong(str)));
    }

    /**
     * 根据日历获取当前时间,星期
     */
    public static String getWeek() {
        String week = "";
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_WEEK);
        switch (day) {
            case 1:
                week = "星期天";
                break;
            case 2:
                week = "星期一";
                break;
            case 3:
                week = "星期二";
                break;
            case 4:
                week = "星期三";
                break;
            case 5:
                week = "星期四";
                break;
            case 6:
                week = "星期五";
                break;
            case 7:
                week = "星期六";
                break;
            default:
                week = "Invalid data";
                break;

        }
        return week;
    }

    /**
     * 将日期转换成date时间
     *
     * @return
     */
    public static Date getDateFromCalendar(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        return calendar.getTime();
    }

    /**
     * 将date类型换成Calendar类型
     * @param date
     * @return
     */
    public static Calendar getCalendarFromDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**************************************jdk8新特性***********************************************/
    public static void clockTest(){
        Clock clock = Clock.systemUTC();
        System.out.println(clock.millis());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateTimeFormatter.format(LocalDateTime.now()));
        System.out.println(LocalDate.now());
        System.out.println(LocalDateTime.now());
    }

    /**
     * ***********************************jdk8新特性*****end*************************************
     */

    public static void main(String[] args) {
        System.out.println(getNowDateTime());
        System.out.println(getNowDateTimeFromTimesStamp());
        System.out.println(getWeek());
        System.out.println(getDateFromCalendar(2019, 3, 11));
        System.out.println(getCalendarFromDate(new Date()).getTime());
        clockTest();
    }
}

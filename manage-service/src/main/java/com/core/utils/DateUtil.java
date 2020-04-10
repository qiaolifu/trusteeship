package com.core.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 作者: 廖亮
 * 时间: 2019.1.15 2:25:21 PM
 * 描述: 日期处理类
 */
public class DateUtil {
    /**
     * 标准时间格式
     **/
    public static final String DATE_TIME_ALL_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 年月日时间格式
     **/
    public static final String DATE_ONLY_FORMAT = "yyyy-MM-dd";

    /**
     * 年月日时间格式
     **/
    public static final String DATE_ONLY_WITHOUT_UNDERLINE_FORMAT = "yyyyMMdd";

    /**
     * 日期样式缓存
     **/
    public static final Map<String, DateFormat> dateFormatMap = new HashMap<String, DateFormat>();

    static {
        dateFormatMap.put(DATE_TIME_ALL_FORMAT, new SimpleDateFormat(DATE_TIME_ALL_FORMAT));
        dateFormatMap.put(DATE_ONLY_FORMAT, new SimpleDateFormat(DATE_ONLY_FORMAT));
        dateFormatMap.put(DATE_ONLY_WITHOUT_UNDERLINE_FORMAT, new SimpleDateFormat(DATE_ONLY_WITHOUT_UNDERLINE_FORMAT));
    }

    /**
     * 获取指定日期的零点 如 2012-01-12  21:23:34 得到2012-01-12  00:00:00
     *
     * @param date 时间
     * @return 时间
     */
    public static Date getZeroTime(Date date) {

        Calendar startDate = Calendar.getInstance();
        startDate.setTime(date);
        startDate.set(Calendar.AM_PM, Calendar.AM);
        startDate.set(Calendar.HOUR, 0);
        startDate.set(Calendar.MINUTE, 0);
        startDate.set(Calendar.SECOND, 0);
        return startDate.getTime();
    }

    /**
     * 给指定日期添加或减去对应的天数
     *
     * @param date   日期对象
     * @param amount 秒数
     * @return date
     */
    public static Date addYear(Date date, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, amount);
        return cal.getTime();
    }

    /**
     * 给指定日期添加或减去对应的天数
     *
     * @param date   日期对象
     * @param amount 秒数
     * @return date
     */
    public static Date addDay(Date date, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, amount);
        return cal.getTime();
    }

    /**
     * 给指定日期添加或减去对应的天数
     *
     * @param date   日期对象
     * @param amount 秒数
     * @return date
     */
    public static Date addHour(Date date, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, amount);
        return cal.getTime();
    }

    /**
     * 给指定日期添加或减去对应的天数
     *
     * @param date   日期对象
     * @param amount 秒数
     * @return date
     */
    public static Date addMinute(Date date, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, amount);
        return cal.getTime();
    }

    /**
     * 给指定日期添加或减去对应的秒数
     *
     * @param date   日期对象
     * @param amount 秒数
     * @return date
     */
    public static Date addSecond(Date date, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.SECOND, amount);
        return cal.getTime();
    }

    /**
     * 通过给定时间串和格式返回对应的日期
     *
     * @param datetime 时间
     * @param pattern  样式
     * @return
     */
    public static Date parseDate(String datetime, String pattern) {
        Date date = null;
        if (null == pattern || "" == pattern) return null;
        pattern = (null == pattern || "" == pattern) ? DATE_ONLY_FORMAT : DATE_TIME_ALL_FORMAT;
        try {
            date = dateFormatMap.get(pattern).parse(datetime);
        } catch (Exception e) {
        }
        return date;
    }
    /**
     * 返回格式化的日期格式 默认 yyyy-MM-dd HH:mm:ss
     * @param date
     * @return String
     */
    public static String parse2Str(Date date) {
        return parse2Str(date,DATE_TIME_ALL_FORMAT);
    }
    /**
     * 返回格式化的日期格式
     * @param date
     * @param pattern
     * @return String
     */
    public static String parse2Str(Date date,String pattern) {
        if(null == pattern) pattern =  DATE_TIME_ALL_FORMAT;
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }




    /**
     * 测试主方法
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(parse2Str(new Date()));
    }
}

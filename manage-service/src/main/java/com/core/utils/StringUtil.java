package com.core.utils;

import java.util.UUID;

/**
 * 字符串工具类
 */
public class StringUtil {
    /**
     *
     * @param text 原始文字
     * @param source 源字符串
     * @param des  目标字符串
     * @return 替换后的字符串
     */
    public static String replaceLast(String text, String source, String des) {
        int pos = text.lastIndexOf(source);
        if (pos > -1) return text.substring(0, pos) + des + text.substring(pos + source.length());
        return text;
    }

    /**
     *  获取首字母大写的字符串
     * @param str
     * @return 返回首字母大写的字符串
     */
    public static String capitalize(String str) {
        if (null == str || str.length() == 0) return str;
        return new StringBuffer(str).append(Character.toTitleCase(str.charAt(0))).append(str.substring(1)).toString();
    }

    /**
     * 获取首字母小写的字符串
     * @param str
     * @return 返回首字母为小写的字符串
     */
    public static String unCapitalize(String str) {
        if (null == str || str.length() == 0) return str;
        return new StringBuffer(str).append(Character.toLowerCase(str.charAt(0))).append(str.substring(1)).toString();
    }

    /**
     *  获取去掉中划线的32位小写uuid
     * @return
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }

    /**
     *  获取后缀
     * @return
     */
    public static String suffix(String source) {
        if(null == source || source.length() == 0) return source;
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }

    public static void main(String[] args) {
        System.out.println(replaceLast("aaa.aaaa.txt",".","\\"));
    }
}

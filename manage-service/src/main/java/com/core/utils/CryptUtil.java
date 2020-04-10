package com.core.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 *
 * 作者: 廖亮
 * 时间: 2019.1.18 2:25:21 PM
 * 描述: 加解密工具类
 *
 */
public class CryptUtil {

    /**
     * md5加密
     * @param text 源字符串
     * @return md5加密后返回的字符串
     */
    public static String md5(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(text.getBytes());
            byte b[] = md.digest();
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0,i = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) i += 256;
                if (i < 16) buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
            // 16位的加密
            // return buf.toString().substring(8, 24);
        } catch (NoSuchAlgorithmException e) {
        }
        return text;
    }

    /**
     * base64加密
     * @param source 源字符串
     * @return base64加密
     */
    public static String base64(String source){
        if(null == source){
            return null;
        }
        return Base64.getEncoder().encodeToString(source.getBytes());
    }

    /**
     *  base64解密
     * @param source 源
     * @return base64加密密码
     */
    public static String base64Decode(String source){
        if(null == source){
            return null;
        }
        return new String(Base64.getDecoder().decode(source));
    }

    public static void main(String[] args){
        System.out.println(md5("admin"));
    }
}

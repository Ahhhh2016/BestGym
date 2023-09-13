package com.example.demo.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    /** 空字符串 */
    private static final String NULL_STR = "";

    /**
     * * 判断一个对象是否为空
     *
     * @param object Object
     * @return true：为空 false：非空
     */
    public static boolean isNull(Object object)
    {
        return object == null;
    }

    /**
     * * 判断一个字符串是否为空串
     *
     * @param str String
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(String str)
    {
        return isNull(str) || NULL_STR.equals(str.trim());
    }

    /**
     * * 判断一个字符串是否为非空串
     *
     * @param str String
     * @return true：非空串 false：空串
     */
    public static boolean isNotEmpty(String str)
    {
        return !isEmpty(str);
    }

    /**
     * * 判断一个对象是否非空
     *
     * @param object Object
     * @return true：非空 false：空
     */
    public static boolean isNotNull(Object object)
    {
        return !isNull(object);
    }

    /**
     * 返回第一个数字下标
     *
     * @param str   字符串
     * @return 结果
     */
    public static String substring(String str) {
        Pattern pattern = Pattern.compile("([0-9])");
        Matcher matcher = pattern.matcher(str);
        matcher.find();
        return matcher.group(1);
    }
}

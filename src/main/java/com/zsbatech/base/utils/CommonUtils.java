package com.zsbatech.base.utils;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * description:
 *
 * @author wxcsdb88
 * @since 2017/11/7 09:00:00
 */
public class CommonUtils {
    public static boolean isEmpty(Object obj) {
        return obj == null
                || obj instanceof String && ("".equals(obj) || "null".equals(obj) || "".equals(((String) obj).trim()))
                || obj instanceof List && ((List) obj).size() == 0
                || obj instanceof Map && ((Map) obj).size() == 0;
    }

    /**
     * string array contains at least one null or "" or "null"
     *
     * @param strs
     * @return
     */
    public static boolean isEmpty(String... strs) {
        for (String str : strs) {
            if (str == null || "".equals(str) || "".equals((str).trim()) || "null".equals(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * once object is null or "" or "null", will return true
     *
     * @param objects array
     * @return boolean
     */
    public static boolean isEmpty(Object... objects) {
        for (Object obj : objects) {
            if (isEmpty(obj)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 如果传入的所有元素 均为empty 则返回 true
     *
     * @param objects {@link Object[]}
     * @return boolean
     */
    public static boolean isAllEmpty(Object... objects) {
        int size = objects.length;
        for (Object obj : objects) {
            if (isEmpty(obj)) {
                size--;
            }
        }
        return size == 0;
    }

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }

    /**
     * 生成编号
     *
     * @return String
     */
    public static String generateSerialNo() {
        //yyMMddHHmmss+6 random 18bits
        StringBuffer sb = new StringBuffer(DateUtils.currentDateTimeStr("yyMMddHHmmss"));
        System.out.println(sb.length());
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {//有可能是负数
            hashCodeV = -hashCodeV;
        }
        sb.append(String.format("%06d", hashCodeV).substring(0, 6));
        return sb.toString();
    }

    /**
     * 如果为null 则返回 null
     *
     * @param str
     * @return
     */
    public static String trim(String str) {
        return str == null ? null : str.trim();
    }

    /**
     * 如果为null 则返回 null
     *
     * @param str
     * @param defaultVal
     * @return
     */
    public static String getString(String str, String defaultVal) {
        return str == null ? defaultVal : str.trim();
    }
}

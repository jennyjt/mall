package com.zsbatech.baasKettleManager.util;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;
import com.zsbatech.baasKettleManager.constants.RegxConstants;


public class StringUtil {
    public static String toString(Object o) {
        if (o == null)
            return "";
        else
            return o.toString();
    }

    /**
     * 去除各式各样的空白串
     *
     * @param str
     * @return
     */
    public static String trimAll(String str) {
        String dest = "";
        if (str != null) {
            // 倒数第二个空格与空格键打出来的空格不一样！
            Pattern p = Pattern.compile(RegxConstants.blank);
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    /**
     * 去除头尾的空白串加强
     *
     * @param str
     * @return
     */
    public static String trim(String str) {
        return Pattern.compile("^(" + RegxConstants.blank + ")|(" + RegxConstants.blank + ")$").matcher(str)
                .replaceAll("");
    }

    /**
     * 所有空格替换为标准空格
     *
     * @param str
     * @return
     */
    public static String replaceNomalBlank(String str) {
        String dest = "";
        if (str != null) {
            // 倒数第二个空格与空格键打出来的空格不一样！
            Pattern p = Pattern.compile(RegxConstants.blank);
            Matcher m = p.matcher(str);
            dest = m.replaceAll(" ");
        }
        return dest;
    }

    public static String jointDoubanUrl(String doubanId) {
        return "https://movie.douban.com/subject/" + doubanId + "/";
    }

    /**
     * 去除字符串的“-”和空串
     *
     * @param str
     * @return
     */
    public static String replaceBlank2(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("-|\\s");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    public static String randomString(int len) {
        String randomSource = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < len; i++) {
            int number = new Random().nextInt(62);
            sb.append(randomSource.charAt(number));
        }
        return sb.toString();
    }

    public static String StringLimit(String str, int limit) {
        int strLen = str.length();

        double count = 0.0d;
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < strLen; i++) {
            int asciicode = str.codePointAt(i);
            if (asciicode > 255) {
                count = count + 1.0d;
            } else {
                count = count + 0.5d;
            }
            if (count > limit) {
                sb.append("...");
                break;
            }
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }

    /**
     * 转换为Double类型
     */
    public static Double toDouble(Object val) {
        if (val == null) {
            return 0D;
        }
        try {
            return Double.valueOf(trim(val.toString()));
        } catch (Exception e) {
            return 0D;
        }
    }

    /**
     * 转换为Long类型
     */
    public static Long toLong(Object val) {
        return toDouble(val).longValue();
    }

    /**
     * 字符串转换为字符串数组
     */
    public static String[] toArr(String sendee) {
        String[] toArr = null;
        if (!StringUtils.isEmpty(sendee)) {
            if (sendee.contains(",")) {
                toArr = sendee.split(",");
            } else {
                toArr = new String[]{sendee};
            }
        }
        return toArr;
    }

    /**
     * 将long集合转换为字符串
     *
     * @param list
     * @param sp   拆分符
     * @return
     */
    public static String longListToStr(List<Long> list, String sp) {
        StringBuffer sb = new StringBuffer("");
        for (Long temp : list) {
            sb.append(temp + sp);
        }
        if (sb.length() > 0) {
            sb.substring(0, sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * 是否可转化为数字
     *
     * @param obj
     * @return
     */
    public static boolean isNum(Object obj) {
        if (null == obj) {
            return false;
        }
        try {
            new BigDecimal(obj.toString());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 转化为int型数字, 不可转化时返回0
     *
     * @param obj
     * @return
     */
    public static int toInt(Object obj) {
        if (isNum(obj)) {
            return new Integer(obj.toString());
        } else {
            return 0;
        }
    }

    /**
     * 字符串数组转换成字符串
     *
     * @param strings
     * @return
     */
    public static String toString(String[] strings) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            if (strings.length == 1) {
                return strings[i];
            }
            if (i > 0) {
                stringBuilder = stringBuilder.append("," + strings[i]);
            } else {
                stringBuilder = stringBuilder.append(strings[i]);
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 生成新字符串
     *
     * @param arg
     * @param ch
     * @return
     */
    public static String toString(String arg, char ch) {
        String string = ch + arg;
        return string;
    }
    /**
     * bollean数组转换成字符串
     *
     * @param booleans
     * @return
     */
    public static String toString(Boolean [] booleans) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < booleans.length ; i++){
            if(booleans.length == 1){
                return booleans[i].toString();
            }
            if(i > 0) {
                stringBuilder = stringBuilder.append(","+booleans[i]);
            }else {
                stringBuilder =stringBuilder.append(booleans[i]);
            }
        }
        return  stringBuilder.toString();
    }
}

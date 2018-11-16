package com.zsbatech.base.constants;

/**
 * description: 全局异常参数格式化处理
 *
 * @author wxcsdb88
 * @since 2017/8/24 20:21
 */

public class InitConstant {
    public static Long TIMESTAMP_DIFF;
    public static Long TIMEOUT;
    public static Long RATE_LIMIT_TIMEOUT;
    public static Integer RATE_LIMIT_COUNT;
    public static String RATE_LIMIT_SUFFIX;

    /**
     * SOCKET_TIME_OUT 连接上一个url后，获取response的返回等待时间 ，即在与目标url建立连接后，
     * 等待放回response的最大时间，在规定时间内没有返回响应的话就抛出SocketTimeout
     * 超时时间 10s
     */
    public static int SOCKET_TIME_OUT = 10000;

    /**
     * CONNECT_TIMEOUT 客户端发送请求到与目标url建立起连接的最大时间
     * 超时时间 3s
     */
    public static int CONNECT_TIMEOUT = 3000;

    static {
        TIMESTAMP_DIFF = 600 * 1000L; // 600s
        TIMEOUT = 24 * 60 * 60L; // 86400s 1 day
        RATE_LIMIT_TIMEOUT = 5L; // x s
        RATE_LIMIT_COUNT = 1000; // 1000 p/5s
        RATE_LIMIT_SUFFIX = "_rate_limit";
    }
}

package com.zsbatech.base.async;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * description:
 *
 * @author wxcsdb88
 * @since 2017/11/21 10:13
 */
//@EnableAutoConfiguration
@ConfigurationProperties(prefix = "spring.pool")
public class ThreadPoolConfig {
    /**
     * 核心线程数 线程池维护线程的最少数量
     */
//    @Value("${spring.pool.corePoolSize}")
    private int corePoolSize;

    /**
     * 线程池维护线程的最大数量
     */
//    @Value("${spring.pool.maxPoolSize}")
    private int maxPoolSize;

    /**
     * 线程池维护线程所允许的空闲时间
     */
//    @Value("${spring.pool.keepAliveSeconds}")
    private int keepAliveSeconds;

    /**
     * 线程池所使用的缓冲队列
     */
//    @Value("${spring.pool.queueCapacity}")
    private int queueCapacity;

    public int getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public int getKeepAliveSeconds() {
        return keepAliveSeconds;
    }

    public void setKeepAliveSeconds(int keepAliveSeconds) {
        this.keepAliveSeconds = keepAliveSeconds;
    }

    public int getQueueCapacity() {
        return queueCapacity;
    }

    public void setQueueCapacity(int queueCapacity) {
        this.queueCapacity = queueCapacity;
    }
}

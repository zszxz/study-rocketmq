package com.rocketmq.cost;

/**
 * @author lsc
 * <p> </p>
 */
public class JmsConfig {
    /**
     * 主题名称 主题一般是服务器设置好 而不能在代码里去新建topic（ 如果没有创建好，生产者往该主题发送消息 会报找不到topic错误）
     */
    public static final String TOPIC = "test1";
}

package com.rocketmq.consumer;

import com.rocketmq.cost.JmsConfig;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @author lsc
 * <p> </p>
 */
@Service
@RocketMQMessageListener(consumerGroup = "test1",topic = JmsConfig.TOPIC)
public class RocketMQConsumerListener implements RocketMQListener<String> {

    @Override
    public void onMessage(String s) {
        System.out.println("消费消息："+s);
    }
}

package com.rocketmq.producer;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lsc
 * <p> </p>
 */
@RestController
public class SendService {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;
    /**
     * 发送普通消息
     * convertAndSend(String destination, Object payload) 发送字符串比较方便
     */
    @RequestMapping("/send")
    public void send(){
        rocketMQTemplate.convertAndSend("test1","test-message");
    }
    /**
     * 发送同步消息
     */
    @RequestMapping("/testSyncSend")
    public void testSyncSend(){
        //参数一：topic   如果想添加tag,可以使用"topic:tag"的写法
        //参数二：消息内容
        SendResult sendResult = rocketMQTemplate.syncSend("test1","同步消息测试");
        System.out.println(sendResult);
    }
    /**
     * 发送异步消息
     */
    @RequestMapping("/testASyncSend")
    public void testASyncSend(){
        //参数一：topic   如果想添加tag,可以使用"topic:tag"的写法
        //参数二：消息内容
        //参数三：回调
        rocketMQTemplate.asyncSend("test1", "异步消息测试", new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println(sendResult);
            }
            @Override
            public void onException(Throwable throwable) {
                System.out.println("消息发送异常");
                throwable.printStackTrace();
            }
        });
    }
    /**
     * 发送单向消息
     */
    @RequestMapping("/testOneWay")
    public void testOneWay(){
        for (int i = 0; i <100000000 ; i++) {
            //参数一：topic   如果想添加tag,可以使用"topic:tag"的写法
            //参数二：消息内容
            rocketMQTemplate.sendOneWay("test1","单向消息测试测试下"+i);
        }
    }

}

package com.gdsig.system.service;

import com.gdsig.common.constant.RocketMqConsts;
import com.gdsig.common.util.DateUtil;
import com.gdsig.security.pojo.Account;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author xs
 * @date 2022/12/22上午 9:57
 */

@Service
public class RocketMqTestServiceImpl implements RocketMqTestService {

    @Resource
    RocketMQTemplate rocketMqTemplate;

    public static final String TOPIC = "system_topic";

    @Override
    public void send() {

        // TODO: 返回消息失败信息、事务消息、死信队列的监听..

        // TODO: 返回消息失败信息、事务消息、死信队列的监听.
        Account account = new Account();
        account.setId("1");
        account.setNumber("1...");
        Message<Account> msg = MessageBuilder.withPayload(account).build();
        rocketMqTemplate.send(RocketMqConsts.SYSTEM_TOPIC, msg);
        System.out.println("发送消息：" + account);
    }

    @Override
    public void syncSend() {
        Account account = new Account();
        account.setId("1");
        account.setNumber("1...");

        Message<Account> msg = MessageBuilder.withPayload(account).build();
        SendResult sendResult = rocketMqTemplate.syncSend(RocketMqConsts.SYSTEM_TOPIC, msg);
        System.out.println(sendResult.toString());
    }

    @Override
    public void asyncSend() {

        SendCallback callback = new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println(sendResult);
            }

            @Override
            public void onException(Throwable throwable) {
                System.out.println(throwable.getMessage());
            }
        };

        Account account = new Account();
        account.setId("1");
        account.setNumber("1...");
        Message<Account> msg = MessageBuilder.withPayload(account).build();
        rocketMqTemplate.asyncSend(TOPIC, msg, callback);

    }

    /**
     * 延迟消息 （同步消息和异步消息都能够发送延迟发送）
     */
    @Override
    public void delaySend() {
        Account account = new Account();
        account.setId("1");
        account.setNumber("1...");
        Message<Account> msg = MessageBuilder.withPayload(account).build();
        System.out.println(DateUtil.formatDateTime(new Date()) + "发送延迟消息");
        SendResult sendResult = rocketMqTemplate.syncSend(TOPIC, msg, 3000L, 2);
        System.out.println(sendResult.toString());
    }
}

package com.gdsig.system.rocketmq;

import com.gdsig.common.constant.RocketMqConsts;
import com.gdsig.common.util.DateUtil;
import com.gdsig.security.pojo.Account;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author xs
 * @date 2022/12/22上午 10:01
 */

//@Component
//@RocketMQMessageListener(topic = RocketMqConsts.SYSTEM_TOPIC,
//        consumerGroup = RocketMqConsts.SYSTEM_GROUP)
public class TestTagListener implements RocketMQListener<Account>, RocketMQPushConsumerLifecycleListener {

    @Override
    public void onMessage(Account s) {
        System.out.println(RocketMqConsts.TEST_TAG);
        System.out.println(DateUtil.formatDateTime(new Date()) + "接受消息");
        System.out.println(s.toString());
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {
        consumer.setInstanceName(RocketMqConsts.SYSTEM_TOPIC + RocketMqConsts.TEST_TAG);

    }
}

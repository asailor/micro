package com.gdsig.system.service;

/**
 * @author xs
 * @date 2022/12/22上午 9:57
 */
public interface RocketMqTestService {

    /**
     * 发送普通消息
     */
    void send();

    /**
     * 同步消息
     */
    void syncSend();

    /**
     * 异步消息
     */
    void asyncSend();

    /**
     * 延迟消息
     */
    void delaySend();
}

package com.github.johnsonmoon.orderlunch.notify;

import com.dingtalk.chatbot.SendResult;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * @Author: fanxx
 * @Date: 2019/7/29 上午11:12
 * @Description:
 */
@Component
public interface DingDingService {

    /**
     * 发送通知
     * @param webhook
     * @param content
     * @return
     */
    SendResult send(String webhook, String content) throws IOException;

    /**
     * 发送通知并可选择是否@所有人
     * @param webhook
     * @param content
     * @param atAll
     * @return
     */
    SendResult send(String webhook, String content, boolean atAll) throws IOException;

    /**
     * 发送通知并@指定的人，通过手机号匹配具体的人
     * @param webhook
     * @param content
     * @param atMobiles
     * @return
     */
    SendResult send(String webhook, String content, List<String> atMobiles) throws IOException;

    /**
     * 发送通知并@指定的人，同时可选择是否@所有人
     * @param webhook
     * @param content
     * @param atMobiles
     * @param atAll
     * @return
     */
    SendResult send(String webhook, String content, List<String> atMobiles, boolean atAll) throws IOException;
}

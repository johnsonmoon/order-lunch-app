package com.github.johnsonmoon.orderlunch.notify;

import com.dingtalk.chatbot.DingtalkChatbotClient;
import com.dingtalk.chatbot.message.TextMessage;

import com.dingtalk.chatbot.SendResult;

import java.io.IOException;
import java.util.List;

/**
 * @Author: fanxx
 * @Date: 2019/7/29 上午11:13
 * @Description:
 */
public class DingDingAlarm implements DingDingService{

    private DingtalkChatbotClient client = new DingtalkChatbotClient();

    @Override
    public SendResult send(String webhook, String content) throws IOException {
        TextMessage message = new TextMessage(content);
        SendResult result = client.send(webhook, message);
        return result;
    }

    @Override
    public SendResult send(String webhook, String content, boolean atAll) throws IOException {
        TextMessage message = new TextMessage(content);
        message.setIsAtAll(true);
        SendResult result = client.send(webhook, message);
        return result;
    }

    @Override
    public SendResult send(String webhook, String content, List<String> atMobiles) throws IOException {
        TextMessage message = new TextMessage(content);
        message.setAtMobiles(atMobiles);
        SendResult result = client.send(webhook, message);
        return result;
    }

    @Override
    public SendResult send(String webhook, String content, List<String> atMobiles, boolean atAll) throws IOException{
        TextMessage message = new TextMessage(content);
        message.setAtMobiles(atMobiles);
        message.setIsAtAll(true);
        SendResult result = client.send(webhook, message);
        return result;
    }
}


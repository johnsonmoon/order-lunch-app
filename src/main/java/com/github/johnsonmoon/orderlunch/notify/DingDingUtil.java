package com.github.johnsonmoon.orderlunch.notify;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: fanxx
 * @Date: 2019/7/29 上午11:13
 * @Description:
 */
public class DingDingUtil {
    private static final Logger logger = LoggerFactory.getLogger(DingDingUtil.class);

    /**
     * 内容不要包含特殊字符一类的，json格式的数据无法发送
     *
     * @param WEBHOOK_TOKEN
     */
    public static boolean sendMessage(String message, String WEBHOOK_TOKEN) {
        HttpClient httpclient;
        HttpResponse response;
        try {
            httpclient = HttpClients.createDefault();
            HttpPost httppost = new HttpPost(WEBHOOK_TOKEN);
            httppost.addHeader("Content-Type", "application/json; charset=utf-8");
            String textMsg = message;
            StringEntity se = new StringEntity(textMsg, "utf-8");
            httppost.setEntity(se);
            response = httpclient.execute(httppost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return true;
            } else {
                logger.info("send dingding failed,status code is " + response.getStatusLine().getStatusCode());
                return false;
            }
        } catch (Exception e) {
            logger.error("error", e);
            logger.error("sendDingDing exception:", e);
            return false;
        }
    }
}


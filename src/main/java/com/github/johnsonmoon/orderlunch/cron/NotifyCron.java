package com.github.johnsonmoon.orderlunch.cron;

import com.alibaba.fastjson.JSON;
import com.github.johnsonmoon.orderlunch.constant.MemberConstant;
import com.github.johnsonmoon.orderlunch.entity.vo.OrderDetailsVO;
import com.github.johnsonmoon.orderlunch.notify.DingDingAlarm;
import com.github.johnsonmoon.orderlunch.notify.DingDingService;
import com.github.johnsonmoon.orderlunch.util.OrderUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: fanxx
 * @Date: 2019/7/29 上午11:41
 * @Description:
 */
@Component
public class NotifyCron {
    private static final Logger LOG = LoggerFactory.getLogger(NotifyCron.class);

    @Value("${wehook}")
    private String wehook;

    private DingDingAlarm alarm = new DingDingAlarm();

    @Scheduled(cron = "${unorder.member.notifyTime}")
    public void checkAndNotify() {
        LOG.info("开始通知未点餐的小伙伴 ...");
        ArrayList mobiles = new ArrayList();
        OrderDetailsVO orderDetailsVO = OrderUtils.getOrderDetail();
        if(null != orderDetailsVO && !orderDetailsVO.getNotOrders().isEmpty()){
            orderDetailsVO.getNotOrders().stream().forEach(orderVO -> {
                String mobile = MemberConstant.memberMap.get(orderVO.getName());
                mobiles.add(mobile);
                LOG.info("未点餐小伙伴:{},手机号:{}",orderVO.getName(),mobile);
            });
        }else{
            LOG.info("暂无可通知对象:{}",JSON.toJSONString(orderDetailsVO));
        }
        try {
            if(!mobiles.isEmpty()){
                alarm.send(wehook,"没报名的小伙伴抓紧辣~",mobiles);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

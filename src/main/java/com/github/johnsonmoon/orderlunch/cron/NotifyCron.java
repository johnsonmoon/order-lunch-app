package com.github.johnsonmoon.orderlunch.cron;

import com.alibaba.fastjson.JSON;
import com.github.johnsonmoon.orderlunch.Util.OrderUtil;
import com.github.johnsonmoon.orderlunch.constant.MemberConstant;
import com.github.johnsonmoon.orderlunch.entity.vo.OrderDetailsVO;
import com.github.johnsonmoon.orderlunch.notify.DingDingAlarm;
import com.github.johnsonmoon.orderlunch.notify.DingDingService;
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

    @Value("${wehook}")
    private String wehook;

    private DingDingAlarm alarm = new DingDingAlarm();

    @Scheduled(cron = "${unorder.member.notifyTime}")
    public void checkAndNotify() {
        List<String> mobiles = new ArrayList();
        OrderDetailsVO orderDetailsVO = OrderUtil.getOrderDetail();
        if(null != orderDetailsVO && !orderDetailsVO.getNotOrders().isEmpty()){
            orderDetailsVO.getNotOrders().stream().forEach(orderVO -> {
                String mobile = MemberConstant.memberMap.get(orderVO.getName());
                mobiles.add(mobile);
            });
        }
        try {
            alarm.send(wehook,"亲们，中午点外卖不啦?",mobiles);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

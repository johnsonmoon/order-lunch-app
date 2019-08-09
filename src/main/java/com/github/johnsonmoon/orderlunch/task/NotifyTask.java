package com.github.johnsonmoon.orderlunch.task;

import com.alibaba.fastjson.JSON;
import com.github.johnsonmoon.orderlunch.entity.domain.Member;
import com.github.johnsonmoon.orderlunch.entity.vo.OrderDetailsVO;
import com.github.johnsonmoon.orderlunch.notify.DingDingAlarm;
import com.github.johnsonmoon.orderlunch.service.MemberService;
import com.github.johnsonmoon.orderlunch.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @Author: fanxx
 * @Date: 2019/7/29 上午11:41
 * @Description:
 */
@Component
public class NotifyTask {
    private static final Logger LOG = LoggerFactory.getLogger(NotifyTask.class);

    @Autowired
    private OrderService orderService;
    @Autowired
    private MemberService memberService;

    @Value("${unorder.member.notify-switch-on}")
    private Boolean notifyTaskSwitchOn = true;

    @Value("${unorder.member.wehook}")
    private String wehook;

    private DingDingAlarm alarm = new DingDingAlarm();

    @Scheduled(cron = "${unorder.member.notifyTime}")
    public void checkAndNotify() {
        if (notifyTaskSwitchOn) {
            LOG.info("开始通知未点餐的小伙伴 ...");
            ArrayList mobiles = new ArrayList();
            OrderDetailsVO orderDetailsVO = orderService.getOrderDetail();
            if (null != orderDetailsVO && !orderDetailsVO.getNotOrders().isEmpty()) {
                orderDetailsVO.getNotOrders().stream().forEach(orderVO -> {
                    Member member = memberService.findByName(orderVO.getName());
                    String mobile = member == null ? "" : member.getPhone();
                    mobiles.add(mobile);
                    LOG.info("未点餐小伙伴:{},手机号:{}", orderVO.getName(), mobile);
                });
            } else {
                LOG.info("暂无可通知对象:{}", JSON.toJSONString(orderDetailsVO));
            }
            try {
                if (!mobiles.isEmpty()) {
                    alarm.send(wehook, "没报名的小伙伴抓紧辣~", mobiles);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

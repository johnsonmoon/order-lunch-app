package com.github.johnsonmoon.orderlunch.action;

import com.github.johnsonmoon.orderlunch.common.ThreadPools;
import com.github.johnsonmoon.orderlunch.constant.MemberConstant;
import com.github.johnsonmoon.orderlunch.entity.domain.Order;
import com.github.johnsonmoon.orderlunch.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Create by xuyh at 2019/7/25 11:48.
 */
@Component
public class OrderCache {
    private static Logger logger = LoggerFactory.getLogger(OrderCache.class);
    private static List<Order> orders = new ArrayList<>();

    @Autowired
    private OrderService orderService;

    public synchronized static Integer getSum() {
        Integer sum = 0;
        for (Order order : orders) {
            sum += order.getAppendNum();
        }
        return sum;
    }

    public synchronized static Integer getLeft() {
        return MemberConstant.memberMap.size() - getSum();
    }

    public synchronized static List<Order> getOrders() {
        return orders;
    }

    public synchronized static void append(Order order) {
        orders.add(order);
    }

    public synchronized static void clear() {
        orders.clear();
    }

    @PostConstruct
    public void startupCheck() {
        ThreadPools.applicationInitializeThreadPool.submit(() -> {
            try {
                Long gte = getTodayStartTime();
                Long lte = getTodayEndTime();
                List<Order> orders = orderService.getPeriodList(gte, lte);
                if (orders != null && !orders.isEmpty()) {
                    orders.forEach(OrderCache::append);
                }
            } catch (Exception e) {
                logger.warn(e.getMessage(), e);
            }
        });
    }

    private static Long getTodayStartTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime().getTime();
    }

    private static Long getTodayEndTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime().getTime();
    }
}

package com.github.johnsonmoon.orderlunch.action;

import com.github.johnsonmoon.orderlunch.Util.OrderUtil;
import com.github.johnsonmoon.orderlunch.constant.MemberConstant;
import com.github.johnsonmoon.orderlunch.entity.domain.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by xuyh at 2019/7/25 11:48.
 */
public class OrderCache {
    private static List<Order> orders = new ArrayList<>();

    public synchronized static Order getLastOne() {
        if (orders.isEmpty()) {
            return null;
        }
        return orders.get(orders.size() - 1);
    }

    public synchronized static Integer getSum() {
        Integer sum = 0;
        for (Order order : orders) {
            sum += order.getAppendNum();
        }
        return sum;
    }

    public synchronized static Integer getLeft() {
        return MemberConstant.memberSet.size() - getSum();
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
}

package com.github.johnsonmoon.orderlunch.util;

import com.github.johnsonmoon.orderlunch.action.OrderCache;
import com.github.johnsonmoon.orderlunch.constant.MemberConstant;
import com.github.johnsonmoon.orderlunch.entity.domain.Order;
import com.github.johnsonmoon.orderlunch.entity.vo.OrderDetailsVO;
import com.github.johnsonmoon.orderlunch.entity.vo.OrderVO;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: fanxx
 * @Date: 2019/7/26 下午5:52
 * @Description:
 */
public class OrderUtils {
    public static boolean validateMember(String name){
        return MemberConstant.memberMap.containsKey(name);
    }

    public static OrderDetailsVO getOrderDetail(){
        OrderDetailsVO orderDetailsVO = new OrderDetailsVO();
        List<Order> orders = new ArrayList<>(OrderCache.getOrders());
        List<OrderVO> orderVOS = new ArrayList<>();
        for (int index = 0; index < orders.size(); index++) {
            Order order = orders.get(index);
            OrderVO orderVO = new OrderVO();
            orderVO.setName(order.getName());
            orderVO.setNumber(index + 1);
            orderVO.setAppendNum(order.getAppendNum());
            orderVO.setIpAddress(order.getIpAddress());
            orderVO.setOrderTime(order.getOrderTime());
            orderVO.setRemark(order.getRemark());
            orderVOS.add(orderVO);
        }
        orderDetailsVO.setOrders(orderVOS);
        Integer sum = 0;
        for (Order order : orders) {
            sum += order.getAppendNum();
        }
        Integer left = MemberConstant.memberMap.size() - sum;
        orderDetailsVO.setSum(sum);
        orderDetailsVO.setLeft(left);
        return orderDetailsVO;
    }
}

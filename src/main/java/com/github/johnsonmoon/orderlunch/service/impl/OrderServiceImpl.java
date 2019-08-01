package com.github.johnsonmoon.orderlunch.service.impl;

import com.github.johnsonmoon.orderlunch.action.OrderCache;
import com.github.johnsonmoon.orderlunch.entity.domain.Order;
import com.github.johnsonmoon.orderlunch.entity.vo.OrderDetailsVO;
import com.github.johnsonmoon.orderlunch.entity.vo.OrderVO;
import com.github.johnsonmoon.orderlunch.repository.MemberRepository;
import com.github.johnsonmoon.orderlunch.repository.OrderRepository;
import com.github.johnsonmoon.orderlunch.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by xuyh at 2019/7/30 11:27.
 */
@Component
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Override
    public Long save(Order order) {
        return orderRepository.save(order).getId();
    }

    @Override
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public List<Order> getPeriodList(Long gte, Long lte) {
        return orderRepository.findAllByOrderTimeBetweenOrderByOrderTimeAsc(gte, lte);
    }

    @Override
    public OrderDetailsVO getOrderDetail() {
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
        Integer left = (int) (memberRepository.count() - sum);
        orderDetailsVO.setSum(sum);
        orderDetailsVO.setLeft(left);
        return orderDetailsVO;
    }
}

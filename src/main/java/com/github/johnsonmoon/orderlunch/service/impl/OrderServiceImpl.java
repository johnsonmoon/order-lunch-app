package com.github.johnsonmoon.orderlunch.service.impl;

import com.github.johnsonmoon.orderlunch.entity.domain.Order;
import com.github.johnsonmoon.orderlunch.repository.OrderRepository;
import com.github.johnsonmoon.orderlunch.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Create by xuyh at 2019/7/30 11:27.
 */
@Component
public class OrderServiceImpl implements OrderService {
    private static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderRepository orderRepository;

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
}

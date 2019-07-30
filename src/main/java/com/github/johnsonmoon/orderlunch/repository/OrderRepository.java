package com.github.johnsonmoon.orderlunch.repository;

import com.github.johnsonmoon.orderlunch.entity.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Create by xuyh at 2019/7/30 11:03.
 */
public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {
    List<Order> findAllByOrderTimeBetweenOrderByOrderTimeAsc(Long gte, Long lte);
}

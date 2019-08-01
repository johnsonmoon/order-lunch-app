package com.github.johnsonmoon.orderlunch.service;

import com.github.johnsonmoon.orderlunch.entity.domain.Order;
import com.github.johnsonmoon.orderlunch.entity.vo.OrderDetailsVO;

import java.util.List;

/**
 * Create by xuyh at 2019/7/30 11:27.
 */
public interface OrderService {
    /**
     * 保存/更新
     *
     * @param order {@link Order}
     * @return {@link Order#id}
     */
    Long save(Order order);

    /**
     * 删除
     *
     * @param id {@link Order#id}
     */
    void deleteById(Long id);

    /**
     * 获取时间段内的数据
     *
     * @param gte 开始时间戳
     * @param lte 结束时间戳
     * @return {@link Order}
     */
    List<Order> getPeriodList(Long gte, Long lte);

    OrderDetailsVO getOrderDetail();
}

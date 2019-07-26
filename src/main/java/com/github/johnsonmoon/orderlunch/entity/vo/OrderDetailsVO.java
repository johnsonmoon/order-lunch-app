package com.github.johnsonmoon.orderlunch.entity.vo;

import java.util.List;

/**
 * Create by xuyh at 2019/7/25 11:58.
 */
public class OrderDetailsVO {
    private Integer sum;
    private List<OrderVO> orders;

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public List<OrderVO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderVO> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "OrderDetailsVO{" +
                "sum=" + sum +
                ", orders=" + orders +
                '}';
    }
}

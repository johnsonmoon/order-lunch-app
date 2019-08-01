package com.github.johnsonmoon.orderlunch.entity.vo;

import java.util.List;

/**
 * Create by xuyh at 2019/7/25 11:58.
 */
public class OrderDetailsVO {
    private Integer sum;
    private Integer left;
    private List<OrderVO> notOrders;
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

    public Integer getLeft() {
        return left;
    }

    public void setLeft(Integer left) {
        this.left = left;
    }

    public List<OrderVO> getNotOrders() {
        return notOrders;
    }

    public void setNotOrders(List<OrderVO> notOrders) {
        this.notOrders = notOrders;
    }

    @Override
    public String toString() {
        return "OrderDetailsVO{" +
                "sum=" + sum +
                ", left=" + left +
                ", orders=" + orders +
                '}';
    }
}

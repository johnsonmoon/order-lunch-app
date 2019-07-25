package com.github.johnsonmoon.orderlunch.entity;

/**
 * Create by xuyh at 2019/7/25 11:45.
 */
public class Order {
    private String ipAddress;
    private Integer appendNum;//+1, -1
    private Long orderTime;

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Integer getAppendNum() {
        return appendNum;
    }

    public void setAppendNum(Integer appendNum) {
        this.appendNum = appendNum;
    }

    public Long getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Long orderTime) {
        this.orderTime = orderTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                ", ipAddress='" + ipAddress + '\'' +
                ", appendNum=" + appendNum +
                ", orderTime=" + orderTime +
                '}';
    }
}

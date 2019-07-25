package com.github.johnsonmoon.orderlunch.entity;

/**
 * Create by xuyh at 2019/7/25 18:57.
 */
public class OrderVO {
    private Integer number;
    private String ipAddress;
    private Integer appendNum;//+1, -1
    private Long orderTime;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

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
        return "OrderVO{" +
                "number=" + number +
                ", ipAddress='" + ipAddress + '\'' +
                ", appendNum=" + appendNum +
                ", orderTime=" + orderTime +
                '}';
    }
}

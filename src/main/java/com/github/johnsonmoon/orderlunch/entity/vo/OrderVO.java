package com.github.johnsonmoon.orderlunch.entity.vo;

/**
 * Create by xuyh at 2019/7/25 18:57.
 */
public class OrderVO {
    private String name;
    private Integer number;
    private String ipAddress;
    private Integer appendNum;//+1, -1
    private Long orderTime;
    private String remark;//备注

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "OrderVO{" +
                "name='" + name + '\'' +
                ", number=" + number +
                ", ipAddress='" + ipAddress + '\'' +
                ", appendNum=" + appendNum +
                ", orderTime=" + orderTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}

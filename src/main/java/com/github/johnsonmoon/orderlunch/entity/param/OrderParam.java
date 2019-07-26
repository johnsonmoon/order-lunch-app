package com.github.johnsonmoon.orderlunch.entity.param;

/**
 * Create by xuyh at 2019/7/26 09:58.
 */
public class OrderParam {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "OrderIncreaseParam{" +
                "name='" + name + '\'' +
                '}';
    }
}

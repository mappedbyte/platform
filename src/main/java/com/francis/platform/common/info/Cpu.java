package com.francis.platform.common.info;

import cn.hutool.core.util.NumberUtil;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author Francis
 * @Date 2022-09-02 10:01
 **/

@Data
public class Cpu implements Serializable {


    /**
     * 核心数
     */
    private int cpuNum;

    /**
     * CPU总的使用率
     */
    private double total;

    /**
     * CPU系统使用率
     */
    private double sys;

    /**
     * CPU用户使用率
     */
    private double used;

    /**
     * CPU当前等待率
     */
    private double wait;

    /**
     * CPU当前空闲率
     */
    private double free;


    public double getTotal() {
        return NumberUtil.round(NumberUtil.mul(total, 100), 2).doubleValue();
    }

    public double getSys() {
        return NumberUtil.round(NumberUtil.mul(sys / total, 100), 2).doubleValue();
    }

    public double getUsed() {
        return NumberUtil.round(NumberUtil.mul(used / total, 100), 2).doubleValue();
    }

    public double getWait() {
        return NumberUtil.round(NumberUtil.mul(wait / total, 100), 2).doubleValue();
    }

    public double getFree() {
        return NumberUtil.round(NumberUtil.mul(free / total, 100), 2).doubleValue();
    }
}

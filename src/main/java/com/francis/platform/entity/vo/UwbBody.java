package com.francis.platform.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author Francis
 * @Date 2022-09-20 15:17
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UwbBody implements Serializable {

    private String tagid;
    private String type;
    private String x;
    private String y;
    private String z;
    private String sos;
    private String state;
    private String floor;
    private String mark;
    private String baoxu;
    private String timestamp;
    private String vbat;








}

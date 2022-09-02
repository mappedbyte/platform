package com.francis.platform.common.info;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author Francis
 * @Date 2022-09-02 10:05
 **/
@Data
public class Sys  implements Serializable {

    /**
     * 服务器名称
     */
    private String computerName;

    /**
     * 服务器Ip
     */
    private String computerIp;

    /**
     * 项目路径
     */
    private String userDir;

    /**
     * 操作系统
     */
    private String osName;

    /**
     * 系统架构
     */
    private String osArch;
}

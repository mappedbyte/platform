package com.francis.platform.common.info;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author Francis
 * @Date 2022-09-02 10:05
 **/
@Data
public class SysFile  implements Serializable {

    /**
     * 盘符路径
     */
    private String dirName;

    /**
     * 盘符类型
     */
    private String sysTypeName;

    /**
     * 文件类型
     */
    private String typeName;

    /**
     * 总大小
     */
    private String total;

    /**
     * 剩余大小
     */
    private String free;

    /**
     * 已经使用量
     */
    private String used;

    /**
     * 资源的使用率
     */
    private double usage;


}

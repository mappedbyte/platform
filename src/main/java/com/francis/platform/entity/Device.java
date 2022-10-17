package com.francis.platform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Francis
 * @Date 2022-09-15 21:12
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_device")
public class Device implements Serializable {
    @TableId(value = "device_id", type = IdType.ASSIGN_ID)
    private Long deviceId;

    /**
     * 设备名称
     */
    @TableField(value = "device_name")
    private String deviceName;

    /**
     * ip地址
     */
    @TableField(value = "device_ip")
    private String deviceIp;

    @TableField(value = "account")
    private String account;

    @TableField(value = "`password`")
    private String password;

    @TableField(value = "port")
    private String port;

    /**
     * 设备类型
     */
    @TableField(value = "device_type")
    private Integer deviceType;

    /**
     * 设备状态 
     */
    @TableField(value = "`status`")
    private Integer status;

    /**
     * 1已删除  0 未删除
     */
    @TableField(value = "is_deleted")
    private Integer isDeleted;

    @TableField(value = "create_time")
    private LocalDateTime createTime;

    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    public static final String COL_DEVICE_ID = "device_id";

    public static final String COL_DEVICE_NAME = "device_name";

    public static final String COL_DEVICE_IP = "device_ip";

    public static final String COL_ACCOUNT = "account";

    public static final String COL_PASSWORD = "password";

    public static final String COL_PORT = "port";

    public static final String COL_DEVICE_TYPE = "device_type";

    public static final String COL_STATUS = "status";

    public static final String COL_IS_DELETED = "is_deleted";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";
}
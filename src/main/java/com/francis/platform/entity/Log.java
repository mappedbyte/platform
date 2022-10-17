package com.francis.platform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Francis
 * @Date 2022-09-15 09:58
 **/
/**
    * 系统日志
    */
@Data
@Builder
@TableName(value = "t_log")
@AllArgsConstructor
@NoArgsConstructor
public class Log {
    /**
     * ID
     */
    @TableId(value = "log_id", type = IdType.INPUT)
    private Long logId;

    @TableField(value = "description")
    private String description;

    @TableField(value = "log_type")
    private String logType;

    @TableField(value = "`method`")
    private String method;

    @TableField(value = "params")
    private String params;

    @TableField(value = "request_ip")
    private String requestIp;

    @TableField(value = "`time`")
    private Long time;

    @TableField(value = "username")
    private String username;

    @TableField(value = "address")
    private String address;

    @TableField(value = "browser")
    private String browser;

    @TableField(value = "exception_detail")
    private String exceptionDetail;

    @TableField(value = "create_time")
    private Date createTime;

    public static final String COL_LOG_ID = "log_id";

    public static final String COL_DESCRIPTION = "description";

    public static final String COL_LOG_TYPE = "log_type";

    public static final String COL_METHOD = "method";

    public static final String COL_PARAMS = "params";

    public static final String COL_REQUEST_IP = "request_ip";

    public static final String COL_TIME = "time";

    public static final String COL_USERNAME = "username";

    public static final String COL_ADDRESS = "address";

    public static final String COL_BROWSER = "browser";

    public static final String COL_EXCEPTION_DETAIL = "exception_detail";

    public static final String COL_CREATE_TIME = "create_time";
}
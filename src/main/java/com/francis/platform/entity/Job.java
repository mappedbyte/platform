package com.francis.platform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Builder;
import lombok.Data;

/**
 * @Author Francis
 * @Date 2022-09-15 09:58
 **/
/**
    * 岗位
    */
@Data
@Builder
@TableName(value = "t_job")
public class Job {
    /**
     * ID
     */
    @TableId(value = "job_id", type = IdType.INPUT)
    private Long jobId;

    /**
     * 岗位名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 岗位状态
     */
    @TableField(value = "enabled")
    private Boolean enabled;

    /**
     * 排序
     */
    @TableField(value = "job_sort")
    private Integer jobSort;

    /**
     * 创建者
     */
    @TableField(value = "create_by")
    private String createBy;

    /**
     * 更新者
     */
    @TableField(value = "update_by")
    private String updateBy;

    /**
     * 创建日期
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    public static final String COL_JOB_ID = "job_id";

    public static final String COL_NAME = "name";

    public static final String COL_ENABLED = "enabled";

    public static final String COL_JOB_SORT = "job_sort";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_UPDATE_BY = "update_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";
}
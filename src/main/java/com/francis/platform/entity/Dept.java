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
    * 部门
    */
@Data
@Builder
@TableName(value = "t_dept")
public class Dept {
    /**
     * ID
     */
    @TableId(value = "dept_id", type = IdType.INPUT)
    private Long deptId;

    /**
     * 上级部门
     */
    @TableField(value = "pid")
    private Long pid;

    /**
     * 子部门数目
     */
    @TableField(value = "sub_count")
    private Integer subCount;

    /**
     * 名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 排序
     */
    @TableField(value = "dept_sort")
    private Integer deptSort;

    /**
     * 状态
     */
    @TableField(value = "enabled")
    private Boolean enabled;

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

    public static final String COL_DEPT_ID = "dept_id";

    public static final String COL_PID = "pid";

    public static final String COL_SUB_COUNT = "sub_count";

    public static final String COL_NAME = "name";

    public static final String COL_DEPT_SORT = "dept_sort";

    public static final String COL_ENABLED = "enabled";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_UPDATE_BY = "update_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";
}
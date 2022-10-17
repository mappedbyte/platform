package com.francis.platform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @Author Francis
 * @Date 2022-09-15 10:39
 **/
@Data
@Builder
@TableName(value = "t_person_dept")
@AllArgsConstructor
@NoArgsConstructor
public class PersonDept {
    @TableId(value = "person_dept_id", type = IdType.ASSIGN_ID)
    private Long personDeptId;

    /**
     * 人员部门
     */
    @TableField(value = "person_dept_name")
    @NotBlank(message = "部门名称不可以为空")
    private String personDeptName;

    /**
     * 排序
     */
    @TableField(value = "sort")
    @NotBlank(message = "部门排序不可以为空")
    private String sort;

    /**
     * 是否启用  1启用 0 未启用
     */
    @TableField(value = "`enable`")
    private Integer enable;

    /**
     * 是否已经删除  0 未删除 1以删除
     */
    @TableField(value = "is_deleted")
    private Integer isDeleted;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    public static final String COL_PERSON_DEPT_ID = "person_dept_id";

    public static final String COL_PERSON_DEPT_NAME = "person_dept_name";

    public static final String COL_SORT = "sort";

    public static final String COL_ENABLE = "enable";

    public static final String COL_IS_DELETED = "is_deleted";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";
}
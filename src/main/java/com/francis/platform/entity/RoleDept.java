package com.francis.platform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

/**
 * @Author Francis
 * @Date 2022-09-15 09:58
 **/
@Data
@Builder
@TableName(value = "t_role_dept")
public class RoleDept {
    /**
     * 角色id
     */
    @TableId(value = "role_id", type = IdType.INPUT)
    private Integer roleId;

    /**
     * 部门id
     */
    @TableField(value = "dept_id" )
    private Integer deptId;

    public static final String COL_ROLE_ID = "role_id";

    public static final String COL_DEPT_ID = "dept_id";
}
package com.francis.platform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @Author Francis
 * @Date 2022-09-15 10:43
 **/
@Data
@Builder
@TableName(value = "t_person")
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @TableId(value = "person_id", type = IdType.ASSIGN_ID)
    private Long personId;

    /**
     * 姓名
     */
    @TableField(value = "person_name")
    @NotBlank(message = "姓名不可以为空")
    private String personName;

    /**
     * 卡号
     */
    @TableField(value = "id_card")
    private String idCard;

    /**
     * 身份证
     */
    @TableField(value = "id_numer")
    private String idNumer;

    @TableField(value = "person_dept_id")
    @NotNull(message = "人员部门不可以为空")
    private Long personDeptId;

    @TableField(value = "`status`")
    private Integer status;

    /**
     * 是否已经删除  0 未删除 1已经删除
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

    public static final String COL_PERSON_ID = "person_id";

    public static final String COL_PERSON_NAME = "person_name";

    public static final String COL_ID_CARD = "id_card";

    public static final String COL_ID_NUMER = "id_numer";

    public static final String COL_PERSON_DEPT_ID = "person_dept_id";

    public static final String COL_STATUS = "status";

    public static final String COL_IS_DELETED = "is_deleted";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";
}
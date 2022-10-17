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
@TableName(value = "t_user_job")
public class UserJob {
    /**
     * 岗位id
     */
    @TableId(value = "user_id", type = IdType.INPUT)
    private Integer userId;

    /**
     * 工作id
     */
    @TableField(value = "job_id" )
    private Integer jobId;

    public static final String COL_USER_ID = "user_id";

    public static final String COL_JOB_ID = "job_id";
}
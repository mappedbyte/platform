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
    * 数据字典详情
    */
@Data
@Builder
@TableName(value = "t_dict_detail")
public class DictDetail {
    /**
     * ID
     */
    @TableId(value = "detail_id", type = IdType.INPUT)
    private Long detailId;

    /**
     * 字典id
     */
    @TableField(value = "dict_id")
    private Long dictId;

    /**
     * 字典标签
     */
    @TableField(value = "`label`")
    private String label;

    /**
     * 字典值
     */
    @TableField(value = "`value`")
    private String value;

    /**
     * 排序
     */
    @TableField(value = "dict_sort")
    private Integer dictSort;

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

    public static final String COL_DETAIL_ID = "detail_id";

    public static final String COL_DICT_ID = "dict_id";

    public static final String COL_LABEL = "label";

    public static final String COL_VALUE = "value";

    public static final String COL_DICT_SORT = "dict_sort";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_UPDATE_BY = "update_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";
}
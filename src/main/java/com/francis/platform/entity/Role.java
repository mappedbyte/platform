package com.francis.platform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

import com.francis.platform.entity.dto.BaseDto;
import com.francis.platform.entity.dto.RoleDto;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author Francis
 * @Date 2022-10-13 15:04
 **/

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_role")
public class Role extends BaseDto {
    /**
     * id值
     */
    @NotNull(groups = {Update.class},message = "修改时roleId不可以为空")
    @TableId(value = "role_id", type = IdType.ASSIGN_ID)
    private Long roleId;

    /**
     * 名称
     */
    @TableField(value = "role_name")
    @NotBlank(groups = {Update.class,Insert.class} ,message = "角色名称不可以为空")
    private String roleName;

    /**
     * 描述
     */
    @TableField(value = "description")
    @NotBlank(groups = {Update.class,Insert.class} ,message = "角色描述不可以为空")
    private String description;

    /**
     * 数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）
     */
    @TableField(value = "data_scope")
    private String dataScope;

    /**
     * 等级
     */
    @TableField(value = "`level`")
    @NotNull(groups = {Update.class,Insert.class} ,message = "角色等级不可以为空")
    private Integer level;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;


    public RoleDto toRoleDto() {
        return (RoleDto) RoleDto.builder()
                .createTime(createTime)
                .dataScope(dataScope)
                .level(level)
                .roleId(roleId)
                .roleName(roleName)
                .description(description)
                .updateTime(updateTime)
                .build();
    }


    public static final String COL_ROLE_ID = "role_id";

    public static final String COL_ROLE_NAME = "role_name";

    public static final String COL_DESCRIPTION = "description";

    public static final String COL_DATA_SCOPE = "data_scope";

    public static final String COL_LEVEL = "level";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";
}
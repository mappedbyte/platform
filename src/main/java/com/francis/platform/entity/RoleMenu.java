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
@TableName(value = "t_role_menu")
public class RoleMenu {
    /**
     * 角色id
     */
    @TableId(value = "role_id", type = IdType.INPUT)
    private Long roleId;

    /**
     * 菜单id
     */
    @TableField(value = "menu_id" )
    private Long menuId;

    public static final String COL_ROLE_ID = "role_id";

    public static final String COL_MENU_ID = "menu_id";
}
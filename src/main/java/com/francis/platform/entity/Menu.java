package com.francis.platform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

import com.francis.platform.entity.dto.BaseDto;
import com.francis.platform.entity.dto.MenuDto;import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author Francis
 * @Date 2022-10-14 10:07
 **/

/**
 * 系统菜单
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_menu")
public class Menu {
    public static final String COL_PARENT_ID = "parent_id";
    public static final String COL_MENU_NAME = "menu_name";
    public static final String COL_URL = "url";
    public static final String COL_SORT = "sort";
    /**
     * ID
     */
    @TableId(value = "menu_id", type = IdType.ASSIGN_ID)
    @NotNull(groups = {BaseDto.Update.class},message = "修改时菜单Id不可以为空")
    private Long menuId;

    /**
     * 上级菜单ID
     */
    @TableField(value = "pid")
    @NotNull(groups = {BaseDto.Insert.class, BaseDto.Update.class},message = "上级菜单不可以为空")
    private Long pid;

    /**
     * 子菜单数目
     */
    @TableField(value = "sub_count")
    private Integer subCount;

    /**
     * 菜单类型
     */
    @TableField(value = "`type`")
    @NotNull(groups = {BaseDto.Insert.class, BaseDto.Update.class},message = "菜单类型不可以为空")
    private Integer type;

    /**
     * 菜单标题
     */
    @TableField(value = "title")
    @NotBlank(groups = {BaseDto.Insert.class, BaseDto.Update.class},message = "菜单类型不可以为空")
    private String title;

    /**
     * 组件名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 组件
     */
    @TableField(value = "component")
    private String component;

    /**
     * 排序
     */
    @TableField(value = "menu_sort")
    private Integer menuSort;

    /**
     * 图标
     */
    @TableField(value = "icon")
    private String icon;

    /**
     * 链接地址
     */
    @TableField(value = "`path`")
    private String path;

    /**
     * 是否外链
     */
    @TableField(value = "i_frame")
    private Boolean iFrame;

    /**
     * 缓存
     */
    @TableField(value = "`cache`")
    private Boolean cache;

    /**
     * 隐藏
     */
    @TableField(value = "hidden")
    private Boolean hidden;

    /**
     * 权限
     */
    @TableField(value = "permission")
    private String permission;

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

    public static final String COL_MENU_ID = "menu_id";

    public static final String COL_PID = "pid";

    public static final String COL_SUB_COUNT = "sub_count";

    public static final String COL_TYPE = "type";

    public static final String COL_TITLE = "title";

    public static final String COL_NAME = "name";

    public static final String COL_COMPONENT = "component";

    public static final String COL_MENU_SORT = "menu_sort";

    public static final String COL_ICON = "icon";

    public static final String COL_PATH = "path";

    public static final String COL_I_FRAME = "i_frame";

    public static final String COL_CACHE = "cache";

    public static final String COL_HIDDEN = "hidden";

    public static final String COL_PERMISSION = "permission";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_UPDATE_BY = "update_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public MenuDto toMenuDto() {
        MenuDto menuDto = new MenuDto();
        menuDto.setMenuId(menuId);
        menuDto.setTitle(title);
        menuDto.setIcon(icon);
        menuDto.setCreateTime(createTime);
        menuDto.setUpdateTime(updateTime);
        menuDto.setPid(pid);
        menuDto.setPermission(permission);
        menuDto.setMenuSort(menuSort);
        menuDto.setType(type);
        menuDto.setPath(path);
        menuDto.setHidden(hidden);
        menuDto.setType(type);
        menuDto.setSubCount(subCount);
        return menuDto;
       /* MenuDto.builder()
                .createTime(createTime)
                .icon(icon)
                .menuId(menuId)
                .menuName(menuName)
                .parentId(parentId)
                .permission(permission)
                .sort(sort)
                .type(type)
                .url(url)
                .updateTime(updateTime)
                .build();
*/

    }
}
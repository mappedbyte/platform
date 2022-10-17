package com.francis.platform.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.francis.platform.common.exception.ExceptionCatch;
import com.francis.platform.common.response.CommonCode;
import com.francis.platform.common.response.PageResult;
import com.francis.platform.entity.Menu;
import com.francis.platform.entity.Role;
import com.francis.platform.entity.RoleMenu;
import com.francis.platform.entity.RoleUser;
import com.francis.platform.entity.dto.BaseDto;
import com.francis.platform.entity.dto.RoleDto;
import com.francis.platform.service.RoleMenuService;
import com.francis.platform.service.RoleService;
import com.francis.platform.service.RoleUserService;
import com.francis.platform.util.AuthorityTokenUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Francis
 * @Date 2022-10-13 14:44
 **/
@RestController
@RequestMapping("/v1/role")
@AllArgsConstructor
public class RoleController {

    private RoleService roleService;
    private RoleUserService roleUserService;
    private RoleMenuService roleMenuService;


    @GetMapping("/queryList/{page}/{size}")
    public PageResult queryList(@PathVariable("page") int page, @PathVariable("size") int size, String roleName) {
        PageHelper.startPage(page, size);
        Page<RoleDto> roleDtoList = (Page<RoleDto>) roleService.queryList(roleName);
        return new PageResult(roleDtoList.getTotal(), roleDtoList.getResult(), roleDtoList.getPageNum(), roleDtoList.getPages());
    }

    @GetMapping("/info/{roleId}")
    public RoleDto info(@PathVariable("roleId") Long roleId) {
        return roleService.findById(roleId);
    }


    @PostMapping("/add")
    public void add(@Validated(BaseDto.Insert.class) @RequestBody Role role) {
        int count = roleService.count(new QueryWrapper<Role>()
                .lambda().eq(Role::getRoleName, role.getRoleName()));
        if (count > 0) {
            ExceptionCatch.cast(CommonCode.THE_ROLE_NAME_ALREADY_EXISTS);
        }
        checkRoleLevel(role.getLevel());
        roleService.save(role);
    }

    @PostMapping("/updateMenu")
    public void updateMenus(RoleDto role){
        if (CollectionUtil.isNotEmpty(role.getMenus())) {
            List<RoleMenu> roleMenus = new ArrayList<>(role.getMenus().size());
            role.getMenus().stream().map(Menu::getMenuId)
                    .forEach(menuId -> roleMenus.add(RoleMenu.builder()
                            .roleId(role.getRoleId())
                            .menuId(menuId).build()));
            roleMenuService.remove(new QueryWrapper<RoleMenu>()
                    .lambda().eq(RoleMenu::getRoleId, role.getRoleId()));
            roleMenuService.saveBatch(roleMenus);
        }
    }

    @PostMapping("/update")
    public void update(@Validated(BaseDto.Update.class) @RequestBody Role role) {
        Role one = roleService.getById(role.getRoleId());
        if (!one.getRoleName().equals(role.getRoleName())) {
            int count = roleService.count(new QueryWrapper<Role>()
                    .lambda().eq(Role::getRoleName, role.getRoleName()));
            if (count > 0) {
                ExceptionCatch.cast(CommonCode.THE_ROLE_NAME_ALREADY_EXISTS);
            }
        }
        checkRoleLevel(role.getLevel());
        roleService.updateById(role);
    }

    @GetMapping("/delete/{roleId}")
    public void delete(@PathVariable("roleId") Long roleId) {
        int count = roleUserService.count(new QueryWrapper<RoleUser>()
                .lambda().eq(RoleUser::getRoleId, roleId));
        if (count > 0) {
            ExceptionCatch.cast(CommonCode.THE_ROLE_HAS_BEEN_ASSOCIATED);
        }
        roleUserService.remove(new QueryWrapper<RoleUser>()
                .lambda().eq(RoleUser::getRoleId, roleId));
        roleService.removeById(roleId);
        roleMenuService.remove(new QueryWrapper<RoleMenu>()
                .lambda().eq(RoleMenu::getRoleId, roleId));
    }


    public void checkRoleLevel(Integer level) {
        Integer minLevel = roleService.findByUsersId(AuthorityTokenUtil.getUserId()).getLevel();
        if (level < minLevel) {
            ExceptionCatch.cast(CommonCode.INSUFFICIENT_ROLE_LEVEL);
        }
    }


}

package com.francis.platform.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.francis.platform.common.exception.ExceptionCatch;
import com.francis.platform.common.response.CommonCode;
import com.francis.platform.entity.Menu;
import com.francis.platform.entity.RoleMenu;
import com.francis.platform.entity.dto.BaseDto;
import com.francis.platform.entity.dto.MenuDto;
import com.francis.platform.service.MenuService;
import com.francis.platform.service.RoleMenuService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author Francis
 * @Date 2022-10-13 16:26
 **/
@RestController
@RequestMapping("/v1/menu")
@AllArgsConstructor
public class MenuController {

    private MenuService menuService;
    private RoleMenuService roleMenuService;


    @PostMapping("/update")
    public void update(@Validated(BaseDto.Update.class) @RequestBody Menu menu) {
        if (menu.getPid().equals(menu.getMenuId())) {
            ExceptionCatch.cast(CommonCode.MENU_SUPERIOR_ERROR);
        }
        Menu one = menuService.getById(menu.getMenuId());
        if (one == null) {
            ExceptionCatch.cast(CommonCode.MENU_NOT_EXISTS);
        }
        Long menuPid = menu.getPid();
        Long onePid = one.getMenuId();
        if (!menu.getTitle().equals(one.getTitle())) {
            if (menuService.getOne(new QueryWrapper<Menu>()
                    .lambda().eq(Menu::getTitle, menu.getTitle())) != null) {
                ExceptionCatch.cast(CommonCode.MENU_ALREADY_EXISTS);
            }
        }
        menuService.updateById(menu);
        if (!menuPid.equals(onePid)) {
            updatePidSubCount(onePid);
            updatePidSubCount(menuPid);
        }
    }


    @PostMapping("/add")
    public void add(@Validated(BaseDto.Insert.class) @RequestBody Menu menu) {
        if (menuService.getOne(new QueryWrapper<Menu>()
                .lambda().eq(Menu::getTitle, menu.getTitle())) != null) {
            ExceptionCatch.cast(CommonCode.MENU_ALREADY_EXISTS);
        }
        menu.setSubCount(0);
        menuService.save(menu);
        updatePidSubCount(menu.getPid());
    }

    @GetMapping("/delete/{menuId}")
    public void delete(@PathVariable("menuId") Long menuId) {

        roleMenuService.remove(new QueryWrapper<RoleMenu>()
                .lambda().eq(RoleMenu::getMenuId, menuId));
        Menu one = menuService.getById(menuId);
        menuService.removeById(menuId);
        updatePidSubCount(one.getPid());
    }


    @GetMapping("/info/{menuId}")
    public Menu info(@PathVariable("menuId") Long menuId) {
        return menuService.getById(menuId);
    }


    @GetMapping("/lazy")
    public List<MenuDto> lazy(Long pid) {
        if (pid == null) {
            pid = 0L;
        }
        return menuService.list(new QueryWrapper<Menu>()
                .lambda().eq(Menu::getPid, pid))
                .stream().map(Menu::toMenuDto).collect(Collectors.toList());
    }

    @GetMapping("/getMenuTree")
    public List<MenuDto> getMenuTree() {
        return getMenuTree(menuService.list(), 0L);
    }


    public List<MenuDto> getMenuTree(List<Menu> menus, Long parentId) {
        List<MenuDto> menuDtoList = new ArrayList<>();
        for (Menu menu : menus) {
            if (menu.getPid().equals(parentId)) {
                MenuDto menuDto = menu.toMenuDto();
                List<MenuDto> children = getMenuTree(menus, menuDto.getMenuId())
                        .stream().sorted(Comparator.comparing(MenuDto::getMenuSort)).collect(Collectors.toList());
                if (!children.isEmpty()) {
                    menuDto.setHasChildren(true);
                }
                menuDto.setChildren(children);
                menuDtoList.add(menuDto);
            }
        }
        return menuDtoList.stream().sorted(Comparator.comparing(MenuDto::getMenuSort)).collect(Collectors.toList());
    }


    @GetMapping("/child/{menuId}")
    public Set<Long> child(@PathVariable("menuId") Long menuId) {
        return getMenuTree(menuService.list(), menuId).stream().map(MenuDto::getMenuId).collect(Collectors.toSet());
    }

    public void updatePidSubCount(Long menuPid) {
        if (!menuPid.equals(0L)) {
            int count = menuService.count(new QueryWrapper<Menu>()
                    .lambda().eq(Menu::getPid, menuPid));
            menuService.update(new UpdateWrapper<Menu>()
                    .lambda().eq(Menu::getMenuId, menuPid)
                    .set(Menu::getSubCount, count));
        }

    }


}

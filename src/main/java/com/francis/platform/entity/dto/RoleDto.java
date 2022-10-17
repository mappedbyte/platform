package com.francis.platform.entity.dto;

import com.francis.platform.entity.Menu;
import com.francis.platform.entity.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @Author Francis
 * @Date 2022-10-13 15:03
 **/

@Data
public class RoleDto extends Role {
    private List<Menu> menus;
}

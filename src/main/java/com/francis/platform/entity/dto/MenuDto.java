package com.francis.platform.entity.dto;

import com.francis.platform.entity.Menu;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @Author Francis
 * @Date 2022-10-13 16:36
 **/
@Data
public class MenuDto extends Menu {

    private List<MenuDto> children;
    private boolean hasChildren;


    public boolean isHasChildren() {
        return this.getSubCount() > 0;
    }
}

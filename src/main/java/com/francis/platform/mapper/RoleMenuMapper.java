package com.francis.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.francis.platform.entity.RoleMenu;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @Author Francis
 * @Date 2022-09-15 09:58
 **/
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {
    int updateBatch(List<RoleMenu> list);

    int updateBatchSelective(List<RoleMenu> list);

    int batchInsert(@Param("list") List<RoleMenu> list);

    int insertOrUpdate(RoleMenu record);

    int insertOrUpdateSelective(RoleMenu record);
}
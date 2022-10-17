package com.francis.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.francis.platform.entity.Role;
import java.util.List;

import com.francis.platform.entity.dto.RoleDto;
import org.apache.ibatis.annotations.Param;

/**
 * @Author Francis
 * @Date 2022-10-13 15:04
 **/
public interface RoleMapper extends BaseMapper<Role> {
    int updateBatch(List<Role> list);

    int updateBatchSelective(List<Role> list);

    int batchInsert(@Param("list") List<Role> list);

    int insertOrUpdate(Role record);

    int insertOrUpdateSelective(Role record);

    List<RoleDto> queryList(@Param("roleName") String roleName);

    RoleDto findById(@Param("roleId") Long roleId);

    Role findByUsersId(@Param("userId") Long userId);
}
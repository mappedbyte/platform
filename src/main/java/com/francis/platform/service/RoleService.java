package com.francis.platform.service;

import com.francis.platform.entity.dto.RoleDto;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import com.francis.platform.entity.Role;
import com.francis.platform.mapper.RoleMapper;

/**
 * @Author Francis
 * @Date 2022-09-15 09:58
 **/
@Service
public class RoleService extends ServiceImpl<RoleMapper, Role> {


    public int updateBatch(List<Role> list) {
        return baseMapper.updateBatch(list);
    }

    public int updateBatchSelective(List<Role> list) {
        return baseMapper.updateBatchSelective(list);
    }

    public int batchInsert(List<Role> list) {
        return baseMapper.batchInsert(list);
    }

    public int insertOrUpdate(Role record) {
        return baseMapper.insertOrUpdate(record);
    }

    public int insertOrUpdateSelective(Role record) {
        return baseMapper.insertOrUpdateSelective(record);
    }

    public List<RoleDto> queryList(String roleName) {
        return baseMapper.queryList(roleName);

    }

    public RoleDto findById(Long roleId) {
        return baseMapper.findById(roleId);
    }

    public Role findByUsersId(Long userId) {
        return baseMapper.findByUsersId(userId);
    }

}



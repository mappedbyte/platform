package com.francis.platform.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import com.francis.platform.mapper.RoleMenuMapper;
import com.francis.platform.entity.RoleMenu;
/**
 * @Author Francis
 * @Date 2022-09-15 09:58
 **/
@Service
public class RoleMenuService extends ServiceImpl<RoleMenuMapper, RoleMenu> {

    
    public int updateBatch(List<RoleMenu> list) {
        return baseMapper.updateBatch(list);
    }
    
    public int updateBatchSelective(List<RoleMenu> list) {
        return baseMapper.updateBatchSelective(list);
    }
    
    public int batchInsert(List<RoleMenu> list) {
        return baseMapper.batchInsert(list);
    }
    
    public int insertOrUpdate(RoleMenu record) {
        return baseMapper.insertOrUpdate(record);
    }
    
    public int insertOrUpdateSelective(RoleMenu record) {
        return baseMapper.insertOrUpdateSelective(record);
    }
}

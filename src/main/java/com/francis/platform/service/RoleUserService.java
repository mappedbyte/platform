package com.francis.platform.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import com.francis.platform.mapper.RoleUserMapper;
import com.francis.platform.entity.RoleUser;
/**
 * @Author Francis
 * @Date 2022-09-15 09:58
 **/
@Service
public class RoleUserService extends ServiceImpl<RoleUserMapper, RoleUser> {

    
    public int updateBatch(List<RoleUser> list) {
        return baseMapper.updateBatch(list);
    }
    
    public int updateBatchSelective(List<RoleUser> list) {
        return baseMapper.updateBatchSelective(list);
    }
    
    public int batchInsert(List<RoleUser> list) {
        return baseMapper.batchInsert(list);
    }
    
    public int insertOrUpdate(RoleUser record) {
        return baseMapper.insertOrUpdate(record);
    }
    
    public int insertOrUpdateSelective(RoleUser record) {
        return baseMapper.insertOrUpdateSelective(record);
    }
}

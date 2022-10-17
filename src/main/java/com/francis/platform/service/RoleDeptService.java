package com.francis.platform.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import com.francis.platform.mapper.RoleDeptMapper;
import com.francis.platform.entity.RoleDept;
/**
 * @Author Francis
 * @Date 2022-09-15 09:58
 **/
@Service
public class RoleDeptService extends ServiceImpl<RoleDeptMapper, RoleDept> {

    
    public int updateBatch(List<RoleDept> list) {
        return baseMapper.updateBatch(list);
    }
    
    public int updateBatchSelective(List<RoleDept> list) {
        return baseMapper.updateBatchSelective(list);
    }
    
    public int batchInsert(List<RoleDept> list) {
        return baseMapper.batchInsert(list);
    }
    
    public int insertOrUpdate(RoleDept record) {
        return baseMapper.insertOrUpdate(record);
    }
    
    public int insertOrUpdateSelective(RoleDept record) {
        return baseMapper.insertOrUpdateSelective(record);
    }
}

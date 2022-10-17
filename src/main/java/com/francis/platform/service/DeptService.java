package com.francis.platform.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import com.francis.platform.entity.Dept;
import com.francis.platform.mapper.DeptMapper;
/**
 * @Author Francis
 * @Date 2022-09-15 09:58
 **/
@Service
public class DeptService extends ServiceImpl<DeptMapper, Dept> {

    
    public int updateBatch(List<Dept> list) {
        return baseMapper.updateBatch(list);
    }
    
    public int updateBatchSelective(List<Dept> list) {
        return baseMapper.updateBatchSelective(list);
    }
    
    public int batchInsert(List<Dept> list) {
        return baseMapper.batchInsert(list);
    }
    
    public int insertOrUpdate(Dept record) {
        return baseMapper.insertOrUpdate(record);
    }
    
    public int insertOrUpdateSelective(Dept record) {
        return baseMapper.insertOrUpdateSelective(record);
    }
}

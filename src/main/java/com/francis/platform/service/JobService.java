package com.francis.platform.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.francis.platform.entity.Job;
import com.francis.platform.mapper.JobMapper;
import java.util.List;
/**
 * @Author Francis
 * @Date 2022-09-15 09:58
 **/
@Service
public class JobService extends ServiceImpl<JobMapper, Job> {

    
    public int updateBatch(List<Job> list) {
        return baseMapper.updateBatch(list);
    }
    
    public int updateBatchSelective(List<Job> list) {
        return baseMapper.updateBatchSelective(list);
    }
    
    public int batchInsert(List<Job> list) {
        return baseMapper.batchInsert(list);
    }
    
    public int insertOrUpdate(Job record) {
        return baseMapper.insertOrUpdate(record);
    }
    
    public int insertOrUpdateSelective(Job record) {
        return baseMapper.insertOrUpdateSelective(record);
    }
}

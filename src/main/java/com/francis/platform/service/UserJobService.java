package com.francis.platform.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import com.francis.platform.mapper.UserJobMapper;
import com.francis.platform.entity.UserJob;
/**
 * @Author Francis
 * @Date 2022-09-15 09:58
 **/
@Service
public class UserJobService extends ServiceImpl<UserJobMapper, UserJob> {

    
    public int updateBatch(List<UserJob> list) {
        return baseMapper.updateBatch(list);
    }
    
    public int updateBatchSelective(List<UserJob> list) {
        return baseMapper.updateBatchSelective(list);
    }
    
    public int batchInsert(List<UserJob> list) {
        return baseMapper.batchInsert(list);
    }
    
    public int insertOrUpdate(UserJob record) {
        return baseMapper.insertOrUpdate(record);
    }
    
    public int insertOrUpdateSelective(UserJob record) {
        return baseMapper.insertOrUpdateSelective(record);
    }
}

package com.francis.platform.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.francis.platform.mapper.LogMapper;
import java.util.List;
import com.francis.platform.entity.Log;
/**
 * @Author Francis
 * @Date 2022-09-15 09:58
 **/
@Service
public class LogService extends ServiceImpl<LogMapper, Log> {

    
    public int updateBatch(List<Log> list) {
        return baseMapper.updateBatch(list);
    }
    
    public int updateBatchSelective(List<Log> list) {
        return baseMapper.updateBatchSelective(list);
    }
    
    public int batchInsert(List<Log> list) {
        return baseMapper.batchInsert(list);
    }
    
    public int insertOrUpdate(Log record) {
        return baseMapper.insertOrUpdate(record);
    }
    
    public int insertOrUpdateSelective(Log record) {
        return baseMapper.insertOrUpdateSelective(record);
    }
}

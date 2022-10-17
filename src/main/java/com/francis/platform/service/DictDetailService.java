package com.francis.platform.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import com.francis.platform.mapper.DictDetailMapper;
import com.francis.platform.entity.DictDetail;
/**
 * @Author Francis
 * @Date 2022-09-15 09:58
 **/
@Service
public class DictDetailService extends ServiceImpl<DictDetailMapper, DictDetail> {

    
    public int updateBatch(List<DictDetail> list) {
        return baseMapper.updateBatch(list);
    }
    
    public int updateBatchSelective(List<DictDetail> list) {
        return baseMapper.updateBatchSelective(list);
    }
    
    public int batchInsert(List<DictDetail> list) {
        return baseMapper.batchInsert(list);
    }
    
    public int insertOrUpdate(DictDetail record) {
        return baseMapper.insertOrUpdate(record);
    }
    
    public int insertOrUpdateSelective(DictDetail record) {
        return baseMapper.insertOrUpdateSelective(record);
    }
}

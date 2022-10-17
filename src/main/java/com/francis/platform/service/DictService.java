package com.francis.platform.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import com.francis.platform.mapper.DictMapper;
import com.francis.platform.entity.Dict;
/**
 * @Author Francis
 * @Date 2022-09-15 09:58
 **/
@Service
public class DictService extends ServiceImpl<DictMapper, Dict> {

    
    public int updateBatch(List<Dict> list) {
        return baseMapper.updateBatch(list);
    }
    
    public int updateBatchSelective(List<Dict> list) {
        return baseMapper.updateBatchSelective(list);
    }
    
    public int batchInsert(List<Dict> list) {
        return baseMapper.batchInsert(list);
    }
    
    public int insertOrUpdate(Dict record) {
        return baseMapper.insertOrUpdate(record);
    }
    
    public int insertOrUpdateSelective(Dict record) {
        return baseMapper.insertOrUpdateSelective(record);
    }
}

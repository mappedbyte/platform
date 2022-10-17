package com.francis.platform.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.francis.platform.mapper.PersonDeptMapper;
import java.util.List;
import com.francis.platform.entity.PersonDept;
/**
 * @Author Francis
 * @Date 2022-09-15 10:39
 **/
@Service
public class PersonDeptService extends ServiceImpl<PersonDeptMapper, PersonDept> {

    
    public int updateBatch(List<PersonDept> list) {
        return baseMapper.updateBatch(list);
    }
    
    public int updateBatchSelective(List<PersonDept> list) {
        return baseMapper.updateBatchSelective(list);
    }
    
    public int batchInsert(List<PersonDept> list) {
        return baseMapper.batchInsert(list);
    }
    
    public int insertOrUpdate(PersonDept record) {
        return baseMapper.insertOrUpdate(record);
    }
    
    public int insertOrUpdateSelective(PersonDept record) {
        return baseMapper.insertOrUpdateSelective(record);
    }
}

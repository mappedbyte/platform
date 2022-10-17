package com.francis.platform.service;

import com.francis.platform.entity.vo.PersonVo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.francis.platform.mapper.PersonMapper;
import java.util.List;
import com.francis.platform.entity.Person;

/**
 * @Author Francis
 * @Date 2022-09-15 10:39
 **/
@Service
public class PersonService extends ServiceImpl<PersonMapper, Person> {


    public int updateBatch(List<Person> list) {
        return baseMapper.updateBatch(list);
    }

    public int updateBatchSelective(List<Person> list) {
        return baseMapper.updateBatchSelective(list);
    }

    public int batchInsert(List<Person> list) {
        return baseMapper.batchInsert(list);
    }

    public int insertOrUpdate(Person record) {
        return baseMapper.insertOrUpdate(record);
    }

    public int insertOrUpdateSelective(Person record) {
        return baseMapper.insertOrUpdateSelective(record);
    }

    public List<PersonVo> findByCondition(String personDeptId,String personName) {

        return baseMapper.findByCondition(personDeptId,personName);
    }
}


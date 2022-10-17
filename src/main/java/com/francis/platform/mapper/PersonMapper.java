package com.francis.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.francis.platform.entity.Person;
import java.util.List;

import com.francis.platform.entity.vo.PersonVo;
import org.apache.ibatis.annotations.Param;

/**
 * @Author Francis
 * @Date 2022-09-15 10:43
 **/
public interface PersonMapper extends BaseMapper<Person> {
    int updateBatch(List<Person> list);

    int updateBatchSelective(List<Person> list);

    int batchInsert(@Param("list") List<Person> list);

    int insertOrUpdate(Person record);

    int insertOrUpdateSelective(Person record);

    List<PersonVo> findByCondition(@Param("personDeptId") String personDeptId,@Param("personName")String personName);

}
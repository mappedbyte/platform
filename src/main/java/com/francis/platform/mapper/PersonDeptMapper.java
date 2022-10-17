package com.francis.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.francis.platform.entity.PersonDept;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @Author Francis
 * @Date 2022-09-15 10:39
 **/
public interface PersonDeptMapper extends BaseMapper<PersonDept> {
    int updateBatch(List<PersonDept> list);

    int updateBatchSelective(List<PersonDept> list);

    int batchInsert(@Param("list") List<PersonDept> list);

    int insertOrUpdate(PersonDept record);

    int insertOrUpdateSelective(PersonDept record);
}
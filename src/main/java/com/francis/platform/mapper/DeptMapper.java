package com.francis.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.francis.platform.entity.Dept;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @Author Francis
 * @Date 2022-09-15 09:58
 **/
public interface DeptMapper extends BaseMapper<Dept> {
    int updateBatch(List<Dept> list);

    int updateBatchSelective(List<Dept> list);

    int batchInsert(@Param("list") List<Dept> list);

    int insertOrUpdate(Dept record);

    int insertOrUpdateSelective(Dept record);
}
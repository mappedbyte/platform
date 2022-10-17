package com.francis.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.francis.platform.entity.Job;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @Author Francis
 * @Date 2022-09-15 09:58
 **/
public interface JobMapper extends BaseMapper<Job> {
    int updateBatch(List<Job> list);

    int updateBatchSelective(List<Job> list);

    int batchInsert(@Param("list") List<Job> list);

    int insertOrUpdate(Job record);

    int insertOrUpdateSelective(Job record);
}
package com.francis.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.francis.platform.entity.Log;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @Author Francis
 * @Date 2022-09-15 09:58
 **/
public interface LogMapper extends BaseMapper<Log> {
    int updateBatch(List<Log> list);

    int updateBatchSelective(List<Log> list);

    int batchInsert(@Param("list") List<Log> list);

    int insertOrUpdate(Log record);

    int insertOrUpdateSelective(Log record);
}
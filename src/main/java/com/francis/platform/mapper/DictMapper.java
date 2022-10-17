package com.francis.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.francis.platform.entity.Dict;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @Author Francis
 * @Date 2022-09-15 09:58
 **/
public interface DictMapper extends BaseMapper<Dict> {
    int updateBatch(List<Dict> list);

    int updateBatchSelective(List<Dict> list);

    int batchInsert(@Param("list") List<Dict> list);

    int insertOrUpdate(Dict record);

    int insertOrUpdateSelective(Dict record);
}
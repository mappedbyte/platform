package com.francis.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.francis.platform.entity.DictDetail;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @Author Francis
 * @Date 2022-09-15 09:58
 **/
public interface DictDetailMapper extends BaseMapper<DictDetail> {
    int updateBatch(List<DictDetail> list);

    int updateBatchSelective(List<DictDetail> list);

    int batchInsert(@Param("list") List<DictDetail> list);

    int insertOrUpdate(DictDetail record);

    int insertOrUpdateSelective(DictDetail record);
}
package com.francis.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.francis.platform.entity.RoleDept;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @Author Francis
 * @Date 2022-09-15 09:58
 **/
public interface RoleDeptMapper extends BaseMapper<RoleDept> {
    int updateBatch(List<RoleDept> list);

    int updateBatchSelective(List<RoleDept> list);

    int batchInsert(@Param("list") List<RoleDept> list);

    int insertOrUpdate(RoleDept record);

    int insertOrUpdateSelective(RoleDept record);
}
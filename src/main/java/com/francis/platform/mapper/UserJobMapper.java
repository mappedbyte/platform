package com.francis.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.francis.platform.entity.UserJob;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @Author Francis
 * @Date 2022-09-15 09:58
 **/
public interface UserJobMapper extends BaseMapper<UserJob> {
    int updateBatch(List<UserJob> list);

    int updateBatchSelective(List<UserJob> list);

    int batchInsert(@Param("list") List<UserJob> list);

    int insertOrUpdate(UserJob record);

    int insertOrUpdateSelective(UserJob record);
}
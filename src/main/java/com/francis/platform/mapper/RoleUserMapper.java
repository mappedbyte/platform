package com.francis.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.francis.platform.entity.RoleUser;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @Author Francis
 * @Date 2022-09-15 09:58
 **/
public interface RoleUserMapper extends BaseMapper<RoleUser> {
    int updateBatch(List<RoleUser> list);

    int updateBatchSelective(List<RoleUser> list);

    int batchInsert(@Param("list") List<RoleUser> list);

    int insertOrUpdate(RoleUser record);

    int insertOrUpdateSelective(RoleUser record);
}
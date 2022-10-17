package com.francis.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.francis.platform.entity.Menu;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @Author Francis
 * @Date 2022-10-14 10:07
 **/
public interface MenuMapper extends BaseMapper<Menu> {
    int updateBatch(List<Menu> list);

    int updateBatchSelective(List<Menu> list);

    int batchInsert(@Param("list") List<Menu> list);

    int insertOrUpdate(Menu record);

    int insertOrUpdateSelective(Menu record);
}
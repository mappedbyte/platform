package com.francis.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.francis.platform.entity.MapInformation;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @Author Francis
 * @Date 2022-09-23 16:45
 **/
public interface MapInformationMapper extends BaseMapper<MapInformation> {
    int updateBatch(List<MapInformation> list);

    int updateBatchSelective(List<MapInformation> list);

    int batchInsert(@Param("list") List<MapInformation> list);

    int insertOrUpdate(MapInformation record);

    int insertOrUpdateSelective(MapInformation record);
}
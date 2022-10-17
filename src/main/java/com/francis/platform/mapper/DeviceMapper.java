package com.francis.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.francis.platform.entity.Device;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @Author Francis
 * @Date 2022-09-15 21:12
 **/
public interface DeviceMapper extends BaseMapper<Device> {
    int updateBatch(List<Device> list);

    int updateBatchSelective(List<Device> list);

    int batchInsert(@Param("list") List<Device> list);

    int insertOrUpdate(Device record);

    int insertOrUpdateSelective(Device record);
}
package com.francis.platform.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import com.francis.platform.mapper.MapInformationMapper;
import com.francis.platform.entity.MapInformation;

/**
 * @Author Francis
 * @Date 2022-09-23 16:42
 **/
@Service
public class MapInformationService extends ServiceImpl<MapInformationMapper, MapInformation> {


    public int updateBatch(List<MapInformation> list) {
        return baseMapper.updateBatch(list);
    }

    public int updateBatchSelective(List<MapInformation> list) {
        return baseMapper.updateBatchSelective(list);
    }

    public int batchInsert(List<MapInformation> list) {
        return baseMapper.batchInsert(list);
    }

    public int insertOrUpdate(MapInformation record) {
        return baseMapper.insertOrUpdate(record);
    }

    public int insertOrUpdateSelective(MapInformation record) {
        return baseMapper.insertOrUpdateSelective(record);
    }
}


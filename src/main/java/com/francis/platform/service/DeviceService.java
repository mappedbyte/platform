package com.francis.platform.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import com.francis.platform.entity.Device;
import com.francis.platform.mapper.DeviceMapper;
/**
 * @Author Francis
 * @Date 2022-09-15 21:12
 **/
@Service
public class DeviceService extends ServiceImpl<DeviceMapper, Device> {

    
    public int updateBatch(List<Device> list) {
        return baseMapper.updateBatch(list);
    }
    
    public int updateBatchSelective(List<Device> list) {
        return baseMapper.updateBatchSelective(list);
    }
    
    public int batchInsert(List<Device> list) {
        return baseMapper.batchInsert(list);
    }
    
    public int insertOrUpdate(Device record) {
        return baseMapper.insertOrUpdate(record);
    }
    
    public int insertOrUpdateSelective(Device record) {
        return baseMapper.insertOrUpdateSelective(record);
    }
}

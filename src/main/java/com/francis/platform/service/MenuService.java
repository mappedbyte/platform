package com.francis.platform.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.francis.platform.mapper.MenuMapper;
import java.util.List;
import com.francis.platform.entity.Menu;

/**
 * @Author Francis
 * @Date 2022-09-15 09:58
 **/
@Service
public class MenuService extends ServiceImpl<MenuMapper, Menu> {


    public int updateBatch(List<Menu> list) {
        return baseMapper.updateBatch(list);
    }

    public int updateBatchSelective(List<Menu> list) {
        return baseMapper.updateBatchSelective(list);
    }

    public int batchInsert(List<Menu> list) {
        return baseMapper.batchInsert(list);
    }

    public int insertOrUpdate(Menu record) {
        return baseMapper.insertOrUpdate(record);
    }

    public int insertOrUpdateSelective(Menu record) {
        return baseMapper.insertOrUpdateSelective(record);
    }
}



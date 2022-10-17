package com.francis.platform.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.francis.platform.common.response.PageResult;
import com.francis.platform.entity.Log;
import com.francis.platform.service.LogService;
import com.francis.platform.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Francis
 * @Date 2022-10-13 10:58
 **/

@RestController
@RequestMapping("/v1/log")
@AllArgsConstructor
public class LogController {

    private LogService logService;


    @GetMapping("/info/{page}/{size}")
    public PageResult info(@PathVariable("page") int page, @PathVariable("size") int size,String username,String description,String startTime,String endTime ) {
        QueryWrapper<Log> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)) {
            wrapper.lambda().eq(Log::getUsername, username);
        }
        if (StringUtils.isNotBlank(description)) {
            wrapper.lambda().like(Log::getDescription, description);
        }
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            wrapper.lambda().ge(Log::getCreateTime, startTime)
                    .le(Log::getCreateTime, endTime);
        }
        wrapper.lambda().orderByDesc(Log::getCreateTime);

        PageHelper.startPage(page, size);
        Page<Log> infoLogListResult = (Page<Log>) logService.list(wrapper);
        return new PageResult(infoLogListResult.getTotal(), infoLogListResult.getResult(), infoLogListResult.getPageNum(), infoLogListResult.getPages());
    }
    @GetMapping("/error/{page}/{size}")
    public PageResult error(@PathVariable("page") int page, @PathVariable("size") int size,String username,String description,String startTime,String endTime) {
        QueryWrapper<Log> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)) {
            wrapper.lambda().eq(Log::getUsername, username);
        }
        if (StringUtils.isNotBlank(description)) {
            wrapper.lambda().like(Log::getDescription, description);
        }
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            wrapper.lambda().ge(Log::getCreateTime, startTime)
                    .le(Log::getCreateTime, endTime);
        }
        wrapper.lambda().orderByDesc(Log::getCreateTime);
        PageHelper.startPage(page, size);
        Page<Log> infoLogListResult = (Page<Log>) logService.list(wrapper);
        return new PageResult(infoLogListResult.getTotal(), infoLogListResult.getResult(), infoLogListResult.getPageNum(), infoLogListResult.getPages());
    }


}

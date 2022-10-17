package com.francis.platform.controller;

import com.francis.platform.common.response.PageResult;
import com.francis.platform.entity.MapInformation;
import com.francis.platform.service.MapInformationService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Francis
 * @Date 2022-09-23 16:43
 **/
@RestController
@RequestMapping("/v1/mapInformation")
@AllArgsConstructor
public class MapInformationController {


    private MapInformationService mapInformationService;


    @PostMapping("/add")
    public void add(@RequestBody @Validated MapInformation mapInformation) {
        mapInformationService.save(mapInformation);
    }


    @GetMapping("/queryList/{page}/{size}")
    public PageResult queryList(@PathVariable("page") int page, @PathVariable("size") int size) {
        PageHelper.startPage(page, size);
        Page<MapInformation> mapInformationList = (Page<MapInformation>) mapInformationService.list();
        return new PageResult(mapInformationList.getTotal(), mapInformationList.getResult(), mapInformationList.getPageNum(), mapInformationList.getPages());
    }


}

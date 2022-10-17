package com.francis.platform.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.francis.platform.common.response.PageResult;
import com.francis.platform.entity.PersonDept;
import com.francis.platform.service.PersonDeptService;
import com.francis.platform.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Francis
 * @Date 2022-09-15 11:18
 **/
@RequestMapping("/v1/personDept")
@RestController
@AllArgsConstructor
public class PersonDeptController {


    private PersonDeptService personDeptService;


    @PostMapping("/add")
    public void add(@RequestBody @Validated PersonDept personDept) {
        personDeptService.save(personDept);
    }

    @GetMapping("/queryList/{page}/{size}")
    public PageResult<PersonDept> queryList(@PathVariable("page") int page, @PathVariable("size") int size, String personDeptName) {
        LambdaQueryWrapper<PersonDept> wrapper = new QueryWrapper<PersonDept>()
                .lambda().eq(PersonDept::getIsDeleted, 0)
                .orderByDesc(PersonDept::getCreateTime);
        if (StringUtils.isNotBlank(personDeptName)) {
            wrapper.like(PersonDept::getPersonDeptName, personDeptName);
        }
        PageHelper.startPage(page, size);
        Page<PersonDept> personDeptList = (Page<PersonDept>) personDeptService.list(
                wrapper);
        return new PageResult<>(personDeptList.getTotal(), personDeptList.getResult(), personDeptList.getPageNum(), personDeptList.getPages());
    }

    @GetMapping("/info/{personDeptId}")
    public PersonDept info(@PathVariable("personDeptId") Long personDeptId) {
        return personDeptService.getById(personDeptId);
    }

    @PostMapping("/update")
    public void update(@RequestBody @Validated PersonDept personDept) {
        personDeptService.updateById(personDept);
    }


    @GetMapping("/delete/{personDeptId}")
    public void delete(@PathVariable("personDeptId") Long personDeptId) {
        personDeptService.update(new UpdateWrapper<PersonDept>()
                .lambda().eq(PersonDept::getPersonDeptId, personDeptId)
                .set(PersonDept::getIsDeleted, 1));
    }

    @GetMapping("/enable/{personDeptId}")
    public void enable(@PathVariable("personDeptId") Long personDeptId) {

    }


}

package com.francis.platform.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.francis.platform.common.response.PageResult;
import com.francis.platform.entity.Person;
import com.francis.platform.entity.vo.PersonVo;
import com.francis.platform.service.PersonService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Francis
 * @Date 2022-09-15 10:44
 **/
@RequestMapping("/v1/person")
@RestController
@AllArgsConstructor
public class PersonController {

    private PersonService personService;


    @PostMapping("/add")
    public void add(@RequestBody @Validated Person person) {
        personService.save(person);
    }

    @GetMapping("/info/{personId}")
    public Person info(@PathVariable("personId") Long personId) {
        return personService.getById(personId);
    }

    @PostMapping("update")
    public void update(@RequestBody @Validated Person person) {
        personService.updateById(person);
    }


    @GetMapping("/delete/{personId}")
    public void delete(@PathVariable("personId") Long personId) {
        personService.update(new UpdateWrapper<Person>()
                .lambda().eq(Person::getPersonId, personId)
                .set(Person::getIsDeleted, 1));
    }


    @GetMapping("/queryList/{page}/{size}")
    public PageResult<PersonVo> queryList(@PathVariable("page") int page, @PathVariable("size") int size,
                                          String personDeptId,String personName) {
        PageHelper.startPage(page, size);
        Page<PersonVo> personList = (Page<PersonVo>) personService.findByCondition(personDeptId,personName);
        return new PageResult<>(personList.getTotal(), personList.getResult(), personList.getPageNum(), personList.getPages());
    }

}

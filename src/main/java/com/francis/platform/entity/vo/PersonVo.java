package com.francis.platform.entity.vo;

import com.francis.platform.entity.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Francis
 * @Date 2022-09-15 16:07
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonVo  extends Person {

    private String personDeptName;


}

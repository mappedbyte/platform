package com.francis.platform.common.response;


import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * 分页结果
 * @param <T>
 */
@Data

public class PageResult<T> implements Serializable {
    private Long total;//返回记录数

    private List<T> rows;//结果
    private int PageNum;
    private int pages;
    private Object other = new HashMap<>();


    public PageResult(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;

    }

    public PageResult(Long total, List<T> rows, Object other) {
        this.total = total;
        this.rows = rows;
        this.other = other;

    }


    public PageResult(Long total, List<T> rows, int pageNum, int pages, Object other) {
        this.total = total;
        this.rows = rows;
        PageNum = pageNum;
        this.pages = pages;
        this.other = other;
    }

    public PageResult(Long total, List<T> rows, int pageNum, int pages) {
        this.total = total;
        this.rows = rows;
        PageNum = pageNum;
        this.pages = pages;
    }
    public PageResult() {

    }



}




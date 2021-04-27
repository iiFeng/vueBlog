package com.example.markerhub.utils;

import lombok.Data;

import java.util.List;

@Data
public class Pager<T> {
    private int currentPage;//分页起始页
    private int size;//每页记录数
    private List<T> rows;//返回的记录集合
    private long total;//总记录条数
    private int Pages;//总记录条数
}

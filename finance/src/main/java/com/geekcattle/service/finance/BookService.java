/*
 * Copyright (c) 2017 <l_iupeiyu@qq.com> All rights reserved.
 */

package com.geekcattle.service.finance;

import com.geekcattle.mapper.console.RoleMapper;
import com.geekcattle.mapper.finance.BookMapper;
import com.geekcattle.model.console.Role;
import com.geekcattle.model.finance.Book;
import com.geekcattle.util.CamelCaseUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Set;

/**
 *
 *  date/21 0021 下午 15:47
 */
@Service
public class BookService {

    @Autowired
    private BookMapper bookMapper;

    public List<Book> getPageList(Book book) {
        PageHelper.offsetPage(book.getOffset(), book.getLimit(), CamelCaseUtil.toUnderlineName(book.getSort())+" "+book.getOrder());
        return bookMapper.selectAll();
    }

    public List<Book> getFromAll(){
        Example example = new Example(Book.class);
        example.createCriteria()
                .andCondition("enable = ", 1);
        return bookMapper.selectByExample(example);
    }

    public Integer getCount(Example example){
        return bookMapper.selectCountByExample(example);
    }

    public Book getById(Integer id) {
        return bookMapper.selectByPrimaryKey(id);
    }

    public List<Book> getById(Book roles) {
        return bookMapper.select( roles);
    }

    public void deleteById(String id) {
        bookMapper.deleteByPrimaryKey(id);
    }

    public void save(Book book) {
        bookMapper.updateByPrimaryKey(book);
    }

    public void insert(Book book){
        bookMapper.insert(book);
    }

}

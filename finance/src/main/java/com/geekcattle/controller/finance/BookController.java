/*
 * Copyright (c) 2017 <l_iupeiyu@qq.com> All rights reserved.
 */

package com.geekcattle.controller.finance;

import com.geekcattle.model.finance.Book;
import com.geekcattle.service.finance.BookService;
import com.geekcattle.util.ReturnUtil;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 *
 *  date/21 0021 下午 15:58
 */
@Controller
@RequestMapping("/console/book")
public class BookController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BookService bookService;




    @RequiresPermissions("book:index")
    @RequestMapping(value = "/index", method = {RequestMethod.GET})
    public String index(Model model) {
        return "console/book/index";
    }

    @RequiresPermissions("book:index")
    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    @ResponseBody
    public ModelMap list(Book book) {
        ModelMap map = new ModelMap();
        List<Book> Lists = bookService.getPageList(book);

        map.put("pageInfo", new PageInfo<Book>(Lists));
        map.put("queryParam", book);
        return ReturnUtil.Success("加载成功", map, null);
    }


    @RequiresPermissions("book:edit")
    @RequestMapping(value = "/from", method = {RequestMethod.GET})
    public String from(Book book, Model model) {
        if (book.getId()!=null) {
            book = bookService.getById(book.getId());
        }
        model.addAttribute("book", book);
        return "console/book/from";
    }

    @RequiresPermissions("book:save")
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    @ResponseBody
    public ModelMap save(@Valid Book book, BindingResult result) {
        if (result.hasErrors()) {
            for (ObjectError er : result.getAllErrors()) return ReturnUtil.Error(er.getDefaultMessage(), null, null);
        }
        try {
            book.setBookkeepingTime(new Date());
            if (book.getId()==null) {
                bookService.insert(book);
            } else {
                bookService.save(book);
            }
            return ReturnUtil.Success("操作成功", null, "/console/book/index");
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.Error("操作失败", null, null);
        }
    }

    @RequiresPermissions("book:delete")
    @RequestMapping(value = "/delete", method = {RequestMethod.GET})
    @ResponseBody
    public ModelMap delete(String[] ids) {
        try {
            if ("null".equals(ids) || "".equals(ids)) {
                return ReturnUtil.Error("Error", null, null);
            } else {
                for (String id : ids) {
                    bookService.deleteById(id);
                }
                return ReturnUtil.Success("操作成功", null, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.Error("操作失败", null, null);
        }
    }

    @RequestMapping(value = "/combobox", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public ModelMap comboBox() {
        ModelMap map = new ModelMap();
        List<Book> roleList = bookService.getFromAll();
        map.put("roleList", roleList);
        return ReturnUtil.Success(null, map, null);
    }

}

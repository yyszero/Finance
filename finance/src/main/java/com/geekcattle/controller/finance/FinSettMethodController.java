/*
 * Copyright (c) 2017 <l_iupeiyu@qq.com> All rights reserved.
 */

package com.geekcattle.controller.finance;

import com.geekcattle.model.finance.FinEvidenceType;
import com.geekcattle.model.finance.FinSettMethod;
import com.geekcattle.service.finance.FinEvidenceTypeService;
import com.geekcattle.service.finance.FinSettMethodService;
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
import java.util.List;

/**
 * 信息管理
 */
@Controller
@RequestMapping("/console/settMethod")
public class FinSettMethodController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FinSettMethodService service;



    @RequiresPermissions("settMethod:index")
    @RequestMapping(value = "/index", method = {RequestMethod.GET})
    public String index(Model model) {

        return "console/settMethod/index";
    }

    @RequiresPermissions("settMethod:index")
    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    @ResponseBody
    public ModelMap list(FinSettMethod settMethod) {
        ModelMap map = new ModelMap();
        List<FinSettMethod> Lists = service.getPageList(settMethod);
        map.put("pageInfo", new PageInfo<FinSettMethod>(Lists));
        map.put("queryParam", settMethod);
        return ReturnUtil.Success("加载成功", map, null);
    }


    @RequiresPermissions("settMethod:edit")
    @RequestMapping(value = "/from", method = {RequestMethod.GET})
    public String from(FinSettMethod settMethod, Model model) {
        if (null!=settMethod.getId()) {
            settMethod = service.selectByPrimaryKey(settMethod.getId());
        }

        model.addAttribute("settMethod", settMethod);
        return "console/settMethod/from";
    }

    @RequiresPermissions("settMethod:save")
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    @ResponseBody
    public ModelMap save(@Valid FinSettMethod settMethod, BindingResult result) {
        if (result.hasErrors()) {
            for (ObjectError er : result.getAllErrors()) return ReturnUtil.Error(er.getDefaultMessage(), null, null);
        }

        try {
            if (null==settMethod.getId()) {
                service.insert(settMethod);
            } else {
                service.updateByPrimaryKeySelective(settMethod);
            }
            return ReturnUtil.Success("操作成功", null, "/console/settMethod/index");
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.Error("操作失败", null, null);
        }
    }

    @RequiresPermissions("settMethod:delete")
    @RequestMapping(value = "/delete", method = {RequestMethod.GET})
    @ResponseBody
    public ModelMap delete(String[] ids) {
        try {
            if ("null".equals(ids) || "".equals(ids)) {
                return ReturnUtil.Error("Error", null, null);
            } else {
                for (String id : ids) {
                    service.deleteByPrimaryKey(Integer.valueOf(id));
                }
                return ReturnUtil.Success("操作成功", null, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.Error("操作失败", null, null);
        }
    }






}

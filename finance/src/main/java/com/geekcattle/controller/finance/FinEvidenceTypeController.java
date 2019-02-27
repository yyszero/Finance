/*
 * Copyright (c) 2017 <l_iupeiyu@qq.com> All rights reserved.
 */

package com.geekcattle.controller.finance;

import com.geekcattle.model.finance.FinAccountCertificate;
import com.geekcattle.model.finance.FinEvidenceType;
import com.geekcattle.service.finance.FinAccountCertificateService;
import com.geekcattle.service.finance.FinEvidenceTypeService;
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
@RequestMapping("/console/evidenceType")
public class FinEvidenceTypeController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FinEvidenceTypeService service;



    @RequiresPermissions("evidenceType:index")
    @RequestMapping(value = "/index", method = {RequestMethod.GET})
    public String index(Model model) {

        return "console/evidenceType/index";
    }

    @RequiresPermissions("evidenceType:index")
    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    @ResponseBody
    public ModelMap list(FinEvidenceType evidenceType) {
        ModelMap map = new ModelMap();
        List<FinEvidenceType> Lists = service.getPageList(evidenceType);
        map.put("pageInfo", new PageInfo<FinEvidenceType>(Lists));
        map.put("queryParam", evidenceType);
        return ReturnUtil.Success("加载成功", map, null);
    }


    @RequiresPermissions("evidenceType:edit")
    @RequestMapping(value = "/from", method = {RequestMethod.GET})
    public String from(FinEvidenceType evidenceType, Model model) {
        if (null!=evidenceType.getId()) {
            evidenceType = service.selectByPrimaryKey(evidenceType.getId());
        }

        model.addAttribute("evidenceType", evidenceType);
        return "console/evidenceType/from";
    }

    @RequiresPermissions("evidenceType:save")
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    @ResponseBody
    public ModelMap save(@Valid FinEvidenceType evidenceType, BindingResult result) {
        if (result.hasErrors()) {
            for (ObjectError er : result.getAllErrors()) return ReturnUtil.Error(er.getDefaultMessage(), null, null);
        }

        try {
            if (null==evidenceType.getId()) {
                service.insert(evidenceType);
            } else {
                service.updateByPrimaryKeySelective(evidenceType);
            }
            return ReturnUtil.Success("操作成功", null, "/console/evidenceType/index");
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.Error("操作失败", null, null);
        }
    }

    @RequiresPermissions("evidenceType:delete")
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

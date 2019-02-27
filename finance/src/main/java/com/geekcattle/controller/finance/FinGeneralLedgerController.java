/*
 * Copyright (c) 2017 <l_iupeiyu@qq.com> All rights reserved.
 */

package com.geekcattle.controller.finance;

import com.geekcattle.model.finance.FinGeneralLedger;
import com.geekcattle.service.finance.FinGeneralLedgerService;
import com.geekcattle.util.ReturnUtil;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 信息管理
 */
@Controller
@RequestMapping("/console/generalLedger")
public class FinGeneralLedgerController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FinGeneralLedgerService  service;



    @RequiresPermissions("generalLedger:index")
    @RequestMapping(value = "/index", method = {RequestMethod.GET})
    public String index(Model model) {

        return "console/generalLedger/index";
    }

    @RequiresPermissions("generalLedger:index")
    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    @ResponseBody
    public ModelMap list(FinGeneralLedger finGeneralLedger) {
        ModelMap map = new ModelMap();
        List<FinGeneralLedger> Lists = service.getPageList(finGeneralLedger);
        map.put("pageInfo", new PageInfo<FinGeneralLedger>(Lists));
        map.put("queryParam", finGeneralLedger);
        return ReturnUtil.Success("加载成功", map, null);
    }









}
